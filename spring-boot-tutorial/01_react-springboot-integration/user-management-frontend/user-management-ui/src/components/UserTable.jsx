import { Table, Image } from "react-bootstrap";
function UserTable({ users }) {
   return (
      <Table striped bordered hover responsive className="align-middle">
         <thead className="table-dark">
            <tr>
               <th>ID</th>
               <th>Avatar</th>
               <th>First Name</th>
               <th>Last Name</th>
               <th>Email</th>
            </tr>
         </thead>
         <tbody>
            {users.length === 0 ? (
               <tr>
                  <td colSpan={5} className="text-center">
                     No users found
                  </td>
               </tr>
            ) : (
               users.map((user) => (
                  <tr key={user.id}>
                     <td>{user.id}</td>
                     <td>
                        <Image
                           src={user.avatar}
                           alt={user.first_name}
                           roundedCircle
                           width={48}
                           height={48}
                        />
                     </td>
                     <td>{user.first_name}</td>
                     <td>{user.last_name}</td>
                     <td>{user.email}</td>
                  </tr>
               ))
            )}
         </tbody>
      </Table>
   );
}
export default UserTable;
