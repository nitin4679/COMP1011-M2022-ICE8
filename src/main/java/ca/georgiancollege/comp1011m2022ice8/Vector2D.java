package ca.georgiancollege.comp1011m2022ice8;

/**
 * @author Nitin Nitin
 */
public class Vector2D implements Comparable<Vector2D> {

    //Private instance members



    private int m_id;
    private float m_x;
    private float m_y;


    //Constructors

    /**
     * Default constructor
     * - set x and y components to 0.0f and id to -1
     */
    public Vector2D()
    {
        m_id = -1;
        setX(0.0f);
        setY(0.0f);
    }

    /**
     * This constructor takes x and y parameters
     * - id is set to -1
     * @param x
     * @param y
     */
    public  Vector2D(float x, float y)
    {
        m_id = -1;
        setX(x);
        setY(y);
    }

    public Vector2D(int id, float x, float y)
    {
        this(x, y);
        setID(id);
    }


//    public float getM_x() {
//        // TODO implement here
//        return 0.0f;
//    }
//
//
//    public void setM_x(float value) {
//        // TODO implement here
//    }

    // public properties (Getters and setters)

    public int getID()
    {
        return m_id;
    }

    public void setID(int id)
    {
        if (id < 0)
        {
            throw new IllegalArgumentException("Vector ID must be greater than zero");
        }else
        {
            m_id = id;
        }
        m_id = id;
    }

    public float getY() {
        return m_y;
    }


    public void setY(float new_y) {
        m_y = new_y;
    }

    /**
     * @return
     */
    public float getX() {
        return m_x;
    }


    public void setX(float new_x) {
        m_x = new_x;
    }

    // computed read-only property
    public float getMagnitude() {
        return Utility.Instance().Distance(new Vector2D(0.0f,0.0f), this);
    }




// Public Methods

    /**
     * This method adds a Vector2D to a Vector2D
     * @param vector
     * @return
     */
    public Vector2D add(Vector2D vector) {
        m_x += vector.getX();
        m_y += vector.getY();
        return this;
    }

    /**
     * @param vector
     * @return
     */
    public Vector2D subtract(Vector2D vector) {
        m_x = vector.getX() - m_x;
        m_y = vector.getY() - m_y;
        return this;

    }

    public String toOneDecimalString()
    {
        var x  = String.format("%.1f", getX());
        var y   = String.format("%.1f", getX());
        return ("("+ x + "," + y + ")");


    }

    // Overridden Methods
    @Override
    public String toString()
    {
        return ("("+ getX() + "," + getY() + ")");
    }

    @Override
    // compare in magnitude order
    public int compareTo(Vector2D otherVector2D)
    {
        if(this.getMagnitude() > otherVector2D.getMagnitude())
        {
            // returning 1 means it's greater than
            return 1;
        }
        else if(this.getMagnitude() < otherVector2D.getMagnitude())
        {
            // returning -1 means it's lesser than
            return -1;
        }
        else
        {
            // returning 0 means it's equal
            return 0;
        }
    }
}