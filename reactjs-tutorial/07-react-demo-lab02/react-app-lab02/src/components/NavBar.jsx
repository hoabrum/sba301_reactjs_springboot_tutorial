import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";
import { Link, NavLink } from "react-router";
import { OrchidsData } from "../common/ListOfOrchids";

const categories = [...new Set(OrchidsData.map((orchid) => orchid.category))];

function NavBar() {
   return (
      <Navbar
         expand="lg"
         className="bg-body-tertiary"
         sticky="top"
         collapseOnSelect
      >
         <Container>
            <Navbar.Brand as={Link} to="/">
               Orchid Shop
            </Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
               <Nav className="me-auto">
                  <Nav.Link as={NavLink} to="/" end eventKey="home">
                     Home
                  </Nav.Link>
                  <Nav.Link as={NavLink} to="/orchids" end eventKey="orchids">
                     Orchids
                  </Nav.Link>
                  <NavDropdown title="Categories" id="categories-dropdown">
                     <NavDropdown.Item as={Link} to="/orchids" eventKey="all">
                        All orchids
                     </NavDropdown.Item>
                     <NavDropdown.Divider />
                     {categories.map((category) => (
                        <NavDropdown.Item
                           key={category}
                           as={Link}
                           to={`/orchids?category=${encodeURIComponent(category)}`}
                           eventKey={category}
                        >
                           {category}
                        </NavDropdown.Item>
                     ))}
                  </NavDropdown>
                  <Nav.Link as={NavLink} to="/about" eventKey="about">
                     About
                  </Nav.Link>
                  <Nav.Link as={NavLink} to="/contact" eventKey="contact">
                     Contact
                  </Nav.Link>
               </Nav>
            </Navbar.Collapse>
         </Container>
      </Navbar>
   );
}
export default NavBar;
