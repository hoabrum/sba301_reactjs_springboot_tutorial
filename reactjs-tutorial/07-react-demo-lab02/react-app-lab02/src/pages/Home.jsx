import { Container, Button } from "react-bootstrap";
import { Link } from "react-router";

export default function Home() {
   return (
      <Container>
         <h1>Welcome to Orchid Shop</h1>
         <p className="mb-4">
            Browse our collection of rare and beautiful orchids.
         </p>
         <Button as={Link} to="/orchids" size="lg">
            View Orchids
         </Button>
      </Container>
   );
}
