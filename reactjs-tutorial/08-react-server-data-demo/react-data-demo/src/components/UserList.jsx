import { useState, useEffect } from "react";
function UserList() {
   const [users, setUsers] = useState([]);
   const [loading, setLoading] = useState(true);
   const [error, setError] = useState(null);

   useEffect(() => {
      fetch("https://reqres.in/api/users")
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
   if (loading) return <p>Loading…</p>;
   if (error) return <p>Error: {error}</p>;

   const userList = Array.isArray(users) ? users : [users];
   return (
      <ul>
         {userList.map((user) => (
            <li key={user.id}>{user.email}</li>
         ))}
      </ul>
   );
}
export default UserList;
