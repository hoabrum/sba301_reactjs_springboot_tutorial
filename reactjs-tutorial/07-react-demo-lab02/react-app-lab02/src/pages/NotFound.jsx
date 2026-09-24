import { Container, Button } from "react-bootstrap";
import { Link } from "react-router";

export default function NotFound() {
   return (
      <Container>
         <h1>404</h1>
         <p className="mb-4">Sorry, the page you are looking for does not exist.</p>
         <Button as={Link} to="/">
            Back to Home
         </Button>
      </Container>
   );
}
