package drawscillate;

public class ShapeFactory {
    /**
    * Function name - getShape
    * Description   - shape Factory
    * @param     ShapesNames
    * @return        - IShapes
     */
    public IShapes getShape(ShapesNames names) {
        
        switch(names) {
        case Heart: return new Heart();
        case Star: return new Star();
        case Rectangle: return new Rectangle();
        case Circle: return new Circle();
        }
        return null;
        
    }
}
