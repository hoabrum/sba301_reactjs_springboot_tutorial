import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import { Routes, Route } from "react-router";
import Layout from "./components/Layout";
import Home from "./pages/Home";
import Orchids from "./components/Orchids";
import OrchidDetail from "./pages/OrchidDetail";
import About from "./pages/About";
import Contact from "./pages/Contact";
import NotFound from "./pages/NotFound";

function App() {
   return (
      <Routes>
         <Route element={<Layout />}>
            <Route index element={<Home />} />
            <Route path="orchids" element={<Orchids />} />
            <Route path="orchids/:id" element={<OrchidDetail />} />
            <Route path="about" element={<About />} />
            <Route path="contact" element={<Contact />} />
            <Route path="*" element={<NotFound />} />
         </Route>
      </Routes>
   );
}

export default App;
