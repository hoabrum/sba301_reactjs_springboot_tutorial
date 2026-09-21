function TableColumns({ data }) {
   return (
      <>
         <td>{data.name}</td>
         <td>{data.email}</td>
         <td>{data.role}</td>
      </>
   );
}

export default function Table({ users }) {
   return (
      <table>
         <tbody>
            {users.map((user) => (
               <tr key={user.id}>
                  <TableColumns data={user} />
               </tr>
            ))}
         </tbody>
      </table>
   );
}
