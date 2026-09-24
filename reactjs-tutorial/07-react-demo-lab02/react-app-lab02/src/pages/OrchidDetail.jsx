import { Container, Row, Col, Badge, Button } from "react-bootstrap";
import { useParams, useNavigate } from "react-router";
import { OrchidsData } from "../common/ListOfOrchids";
import NotFound from "./NotFound";

export default function OrchidDetail() {
   // :id comes from the route path "orchids/:id"
   const { id } = useParams();
   const navigate = useNavigate();
   const orchid = OrchidsData.find((o) => o.id === id);

   if (!orchid) return <NotFound />;

   return (
      <Container className="text-start">
         <Button
            variant="link"
            className="px-0 mb-3"
            onClick={() => navigate(-1)}
         >
            ← Back
         </Button>
         <Row className="g-4">
            <Col md={5}>
               <img
                  src={orchid.image}
                  alt={orchid.orchidName}
                  className="img-fluid rounded"
               />
            </Col>
            <Col md={7}>
               <h2>{orchid.orchidName}</h2>
               <p>
                  <Badge bg="secondary">{orchid.category}</Badge>{" "}
                  {orchid.isSpecial && (
                     <Badge bg="warning" text="dark">
                        Special
                     </Badge>
                  )}
               </p>
               <p>{orchid.description}</p>
            </Col>
         </Row>
      </Container>
   );
}
