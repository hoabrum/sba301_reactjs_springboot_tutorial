import { Outlet } from "react-router";
import NavBar from "./NavBar";

export default function Layout() {
   return (
      <>
         <NavBar />
         <main className="flex-grow-1 py-4">
            <Outlet />
         </main>
         <footer className="border-top py-3 text-body-secondary small">
            © {new Date().getFullYear()} Orchid Shop — SBA301 React Demo
         </footer>
      </>
   );
}
