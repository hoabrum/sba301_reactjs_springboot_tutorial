import "./App.css";
import Dashboard from "./components/Dashboard";

function App() {
   const user = { name: "Ada Lovelace" };
   function handleLogout() {
      console.log("Logging out...");
   }
   return <Dashboard user={user} onLogout={handleLogout} />;
}

export default App;
