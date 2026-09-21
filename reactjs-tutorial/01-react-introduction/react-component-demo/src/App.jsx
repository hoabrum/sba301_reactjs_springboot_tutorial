import Button from "./components/Button";
import ButtonExample from "./components/ButtonExample";
import Greeting from "./components/Greeting";
import GridExample from "./components/GridExample";
import HelloWorld from "./components/HelloWorld";
import Table from "./components/Table";
import Welcome from "./components/Welcome";

function App() {
   const users = [
      {
         id: 1,
         name: "John Doe",
         email: "john.doe@example.com",
         role: "User",
      },
      {
         id: 2,
         name: "Jane Smith",
         email: "jane.smith@example.com",
         role: "Admin",
      },
   ];
   return (
      <>
         <section id="spacer"></section>
         <HelloWorld />
         <Welcome />
         <Greeting />
         <Table users={users} />
         <Button title="Click Me" color="blue" />
         <Button title="Submit" color="green" />
         <Button title="Cancel" color="red" />

         <ButtonExample />
         <GridExample />
      </>
   );
}

export default App;
