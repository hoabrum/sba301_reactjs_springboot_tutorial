import { useState } from "react";
import { Row, Col, Container, Card, Button } from "react-bootstrap";
import { Link, useSearchParams } from "react-router";
import { OrchidsData } from "../shared/ListOfOrchids";
import Modal from "react-bootstrap/Modal";

export default function Orchids() {
   const [show, setShow] = useState(false);

   const [selectedOrchid, setSelectedOrchid] = useState(null);

   // Read the ?category= query param set by the Categories menu
   const [searchParams] = useSearchParams();
   const category = searchParams.get("category");
   const orchids = category
      ? OrchidsData.filter((orchid) => orchid.category === category)
      : OrchidsData;

   const handleClose = () => setShow(false);

   const handleShow = (orchid) => {
      setSelectedOrchid(orchid);
      setShow(true);
   };

   return (
      <Container>
         <h2 className="mb-4">{category ? `${category} Orchids` : "All Orchids"}</h2>
         <Row className="g-4">
            {orchids.map((orchid) => (
               <Col md={3} key={orchid.id}>
                  <Card className="h-100">
                     <Card.Img variant="top" src={orchid.image} />
                     <Card.Body>
                        <Card.Title>{orchid.orchidName}</Card.Title>
                        <Card.Text>{orchid.category}</Card.Text>
                        <div className="d-flex gap-2 justify-content-center">
                           <Button
                              variant="outline-primary"
                              size="sm"
                              onClick={() => handleShow(orchid)}
                           >
                              Quick View
                           </Button>
                           <Button
                              as={Link}
                              to={`/orchids/${orchid.id}`}
                              variant="primary"
                              size="sm"
                           >
                              Details
                           </Button>
                        </div>
                     </Card.Body>
                  </Card>
               </Col>
            ))}
         </Row>
         <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
               <Modal.Title>
                  {selectedOrchid ? selectedOrchid.orchidName : ""}
               </Modal.Title>
            </Modal.Header>
            <Modal.Body>
               {selectedOrchid ? (
                  <div>
                     <img
                        src={selectedOrchid.image}
                        alt={selectedOrchid.orchidName}
                        style={{ width: "100%" }}
                     />
                     <p>{selectedOrchid.description}</p>
                  </div>
               ) : (
                  <p>Loading details...</p>
               )}
            </Modal.Body>
            <Modal.Footer>
               <Button variant="secondary" onClick={handleClose}>
                  Close
               </Button>
            </Modal.Footer>
         </Modal>
      </Container>
   );
}
