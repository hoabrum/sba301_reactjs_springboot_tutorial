package com.usermanagement.controller;

import com.usermanagement.entity.Address;
import com.usermanagement.entity.User;
import com.usermanagement.exception.RecordNotFoundException;
import com.usermanagement.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.dialect.lock.OptimisticForceIncrementLockingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Users", description = "Operations for creating, reading, updating and deleting users")
public class UserRestController {

    private static final String USER_EXAMPLE = """
            {
              "id": 1,
              "firstName": "John",
              "lastName": "Smith",
              "email": "john@gmail.com",
              "address": "Thach That, Ha Noi",
              "image": null,
              "gender": "MALE"
            }""";

    private static final String NEW_USER_EXAMPLE = """
            {
              "firstName": "Mary",
              "lastName": "Trump",
              "email": "mary@gmail.com",
              "address": "Cau Giay, Ha Noi",
              "image": null,
              "gender": "FEMALE"
            }""";

    private static final String SERVER_ERROR_EXAMPLE = """
            {
              "timestamp": "2026-10-07T08:30:00.000+00:00",
              "status": 500,
              "error": "Internal Server Error",
              "path": "/api/v1/users/99"
            }""";

    @Autowired
    private UserRepository userRepository;

    @Operation(
            summary = "Get all users",
            description = "Returns every user stored in the database. Returns 204 No Content when there are no users.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of users",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = User.class)),
                            examples = @ExampleObject(name = "Users", value = "[" + USER_EXAMPLE + "]"))),
            @ApiResponse(responseCode = "204", description = "No users exist", content = @Content)
    })
    @GetMapping("/users")
    public ResponseEntity<?> fetchAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @Operation(
            summary = "Get a user by id",
            description = "Returns the user with the given id, or 404 Not Found if no such user exists.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = User.class),
                            examples = @ExampleObject(name = "User", value = USER_EXAMPLE))),
            @ApiResponse(responseCode = "404", description = "User not found (empty body)", content = @Content)
    })
    @GetMapping("/users/{id}")
    public ResponseEntity<?> fetchUserById(
            @Parameter(description = "Id of the user to retrieve", example = "1", required = true)
            @PathVariable long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Get a user by email",
            description = "Looks up a user by exact email address. "
                    + "If no user has that email, the response is still 200 OK but with an empty body.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found, or empty body when no user matches",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = User.class),
                            examples = @ExampleObject(name = "User", value = USER_EXAMPLE))),
            @ApiResponse(responseCode = "400", description = "The required 'email' query parameter is missing",
                    content = @Content)
    })
    @GetMapping("/user")
    public ResponseEntity<?> fetchUserByEmail(
            @Parameter(description = "Email address of the user to retrieve", example = "john@gmail.com",
                    required = true)
            @RequestParam("email") String email) {
        User user = userRepository.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    @Operation(
            summary = "Create a new user",
            description = "Creates a user and returns it with its generated id. "
                    + "Do not send an id. When provided, firstName must be at least 3 characters.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "User to create",
            required = true,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = User.class),
                    examples = @ExampleObject(name = "New user", value = NEW_USER_EXAMPLE)))
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = User.class),
                            examples = @ExampleObject(name = "Created user", value = USER_EXAMPLE))),
            @ApiResponse(responseCode = "400", description = "Validation failed: firstName is shorter than 3 characters",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                            schema = @Schema(type = "string"),
                            examples = @ExampleObject(value = "Firstname must be greater than 3 characters"))),
            @ApiResponse(responseCode = "500", description = "Database error, e.g. the email is already in use",
                    content = @Content)
    })
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        System.out.println("User Request: " + user);
        if(user.getFirstName() != null && user.getFirstName().length() < 3) {
            return new ResponseEntity<>("Firstname must be greater than 3 characters", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update an existing user",
            description = "Replaces all fields of the user with the given id. "
                    + "The request body must include the same id as the path; "
                    + "if the body has no id, a new user is created instead of updating the existing one.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Full user data to save",
            required = true,
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = User.class),
                    examples = @ExampleObject(name = "Updated user", value = USER_EXAMPLE)))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = User.class),
                            examples = @ExampleObject(name = "Updated user", value = USER_EXAMPLE))),
            @ApiResponse(responseCode = "500",
                    description = "No user with this id (RecordNotFoundException has no handler, so it surfaces as 500)",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = SERVER_ERROR_EXAMPLE)))
    })
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(
            @Parameter(description = "Id of the user to update", example = "1", required = true)
            @PathVariable Long id,
            @RequestBody User user) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new RecordNotFoundException("User with id " + id + " not found");
        }
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a user",
            description = "Deletes the user with the given id and returns a confirmation message.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User deleted",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                            schema = @Schema(type = "string"),
                            examples = @ExampleObject(value = "User with id 1 deleted"))),
            @ApiResponse(responseCode = "500",
                    description = "No user with this id (RecordNotFoundException has no handler, so it surfaces as 500)",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(value = SERVER_ERROR_EXAMPLE)))
    })
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(
            @Parameter(description = "Id of the user to delete", example = "1", required = true)
            @PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new RecordNotFoundException("User with id " + id + " not found");
        }
        userRepository.delete(userOptional.get());
        return new ResponseEntity<>("User with id " + id + " deleted", HttpStatus.OK);
    }

    @PutMapping("/users/addresses/{userId}")
    public ResponseEntity<?> updateUserAddress(@PathVariable Long userId, @RequestBody User user) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new RecordNotFoundException("User with id " + userId + " not found");
        }
        User updatedUser = userOptional.get();

        List<Address> userAddresses = user.getAddresses();
        for(Address address : userAddresses) {
            address.setUser(updatedUser);
        }
        updatedUser.setAddresses(userAddresses);
        return new ResponseEntity<>(userRepository.save(updatedUser), HttpStatus.OK);
    }

    @GetMapping("/users/search")
    public ResponseEntity<?> searchUserInfoByParams(@RequestParam String filter) {
        System.out.println("filter: " + filter);
        return ResponseEntity.ok(userRepository.searchUserInfo(filter));
    }
}
