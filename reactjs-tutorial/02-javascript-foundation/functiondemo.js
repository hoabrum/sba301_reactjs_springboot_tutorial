// Normal function declaration
function add(a, b) {
   return a + b;
}

const result = add(5, 3);
console.log(result); // Output: 8

// Function expression
const multiply = function (a, b) {
   return a * b;
};

const total = multiply(4, 6) + multiply(2, 3);
console.log(total); // Output: 30

// Arrow function
const subtract = (a, b) => {
   return a - b;
};
console.log(subtract(10, 4)); // Output: 6

const user = {
   name: "John Doe",
   age: 30,
   email: "tom@gmail.com",
   greet: function () {
      console.log(
         `Hello, my name is ${this.name} and I am ${this.age} years old.`,
      );
   },
   sayHi: () => {
      console.log(`Hi, I am ${this.name}.`);
   },
};
const [address = "FPT University"] = user;
console.log(address); // Output: FPT University

//const email = user.email;
//const name = user.name;
const { email, name } = user;
//console.log(email + ", " + name); // Output: tom@gmail.com, John Doe

//user.sayHi(); // undefined undefined

const colors = ["red", "green", "blue"];
const [, , thirdColor] = colors;
console.log(thirdColor);
