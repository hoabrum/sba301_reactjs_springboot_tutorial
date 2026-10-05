import { useState } from "react";
import {
   Container,
   Form,
   Button,
   InputGroup,
   Spinner,
   Alert,
} from "react-bootstrap";
import UserTable from "./UserTable";

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

function UserSearchForm() {
   const [userId, setUserId] = useState("");
   const [users, setUsers] = useState([]);
   const [loading, setLoading] = useState(false);
   const [error, setError] = useState(null);

   const handleSearch = (e) => {
      e.preventDefault();
      const id = userId.trim();
      const url = id
         ? `${API_BASE_URL}/users/${encodeURIComponent(id)}`
         : `${API_BASE_URL}/users`;
      setLoading(true);
      setError(null);
      fetch(url)
         .then((res) => {
            if (res.status === 404)
               throw new Error(`User with id ${id} not found`);
            if (!res.ok) throw new Error(`HTTP ${res.status}`);
            return res.json();
         })
         .then((json) =>
            setUsers(Array.isArray(json.data) ? json.data : [json.data]),
         )
         .catch((err) => {
            console.error("Search failed:", err);
            setUsers([]);
            setError(err.message);
         })
         .finally(() => setLoading(false));
   };

   return (
      <Container className="mt-4">
         <h2 className="mb-3">Search User</h2>
         <Form onSubmit={handleSearch} className="mb-3">
            <InputGroup>
               <Form.Control
                  type="number"
                  min={1}
                  placeholder="Enter user id"
                  value={userId}
                  onChange={(e) => setUserId(e.target.value)}
               />
               <Button type="submit" variant="primary" disabled={loading}>
                  {loading ? (
                     <Spinner animation="border" size="sm" />
                  ) : (
                     "Search"
                  )}
               </Button>
            </InputGroup>
         </Form>
         {error && <Alert variant="danger">{error}</Alert>}
         <UserTable users={users} />
      </Container>
   );
}

export default UserSearchForm;
