import { Container, Row, Col } from "react-bootstrap";
function GridExample() {
   return (
      <Container>
         <Row>
            {/* Each Col takes 4 of 12 columns → equal thirds */}
            <Col md={4}>Column 1</Col>
            <Col md={4}>Column 2</Col>
            <Col md={0}>Column 3</Col>
         </Row>
      </Container>
   );
}

export default GridExample;
