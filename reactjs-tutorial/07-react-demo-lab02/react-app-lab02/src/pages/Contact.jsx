import { Container, Form, Button } from "react-bootstrap";

export default function Contact() {
   return (
      <Container style={{ maxWidth: 560 }} className="text-start">
         <h1 className="text-center">Contact</h1>
         <Form onSubmit={(e) => e.preventDefault()}>
            <Form.Group className="mb-3" controlId="contactName">
               <Form.Label>Name</Form.Label>
               <Form.Control type="text" placeholder="Your name" />
            </Form.Group>
            <Form.Group className="mb-3" controlId="contactEmail">
               <Form.Label>Email</Form.Label>
               <Form.Control type="email" placeholder="name@example.com" />
            </Form.Group>
            <Form.Group className="mb-3" controlId="contactMessage">
               <Form.Label>Message</Form.Label>
               <Form.Control as="textarea" rows={4} />
            </Form.Group>
            <Button type="submit">Send</Button>
         </Form>
      </Container>
   );
}
