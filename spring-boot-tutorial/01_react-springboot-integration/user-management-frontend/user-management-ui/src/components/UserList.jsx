import { useState, useEffect } from "react";
import { Container, Spinner, Alert } from "react-bootstrap";
import UserTable from "./UserTable";

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

function UserList() {
   const [users, setUsers] = useState([]);
   const [loading, setLoading] = useState(true);
   const [error, setError] = useState(null);

   useEffect(() => {
      fetch(`${API_BASE_URL}/users`)
         .then((res) => {
            if (!res.ok) throw new Error(`HTTP ${res.status}`);
            return res.json();
         })
         .then((json) => setUsers(json.data))
         .catch((err) => {
            console.error("Fetch failed:", err);
            setError(err.message);
         })
         .finally(() => setLoading(false));
   }, []);
   if (loading)
      return (
         <Container className="text-center mt-5">
            <Spinner animation="border" role="status" />
            <p className="mt-2">Loading…</p>
         </Container>
      );
   if (error)
      return (
         <Container className="mt-5">
            <Alert variant="danger">Error: {error}</Alert>
         </Container>
      );

   const userList = Array.isArray(users) ? users : [users];
   return (
      <Container className="mt-4">
         <h2 className="mb-3">User List</h2>
         <UserTable users={userList} />
      </Container>
   );
}
export default UserList;
