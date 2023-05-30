import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;

public class DemoViewer {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Container pane = frame.getContentPane();

       // Slider to control horizontal rotation
        JSlider headingSlider = new JSlider(0, 360, 180);
        pane.add(headingSlider, BorderLayout.SOUTH);

        // Slider to control vertical rotation
        JSlider pitchSlider = new JSlider(SwingConstants.VERTICAL, -90, 90, 0);
        pane.add(pitchSlider, BorderLayout.EAST);

        // Panel to display render results
        JPanel renderPanel = new JPanel() {
            public void paintComponent(Graphics graphics) {
                Graphics2D graph2 = (Graphics2D) graphics;
                graph2.setColor(Color.BLACK);
                graph2.fillRect(0, 0, getWidth(), getHeight());

                // Rendering magic will happen here
                ArrayList<Triangle> tris = new ArrayList<>();

                tris.add(new Triangle(
                        new Vertex(100, 100, 100),
                        new Vertex(-100, -100, 100),
                        new Vertex(-100, 100, -100),
                        Color.WHITE
                ));

                tris.add(new Triangle(
                        new Vertex(100, 100, 100),
                        new Vertex(-100, -100, 100),
                        new Vertex(100, 100, -100),
                        Color.RED
                ));

                tris.add(new Triangle(
                        new Vertex(-100, 100, -100),
                        new Vertex(100, -100, -100),
                        new Vertex(100, 100, 100),
                        Color.GREEN
                ));

                tris.add(new Triangle(
                        new Vertex(-100, 100, -100),
                        new Vertex(100, -100, -100),
                        new Vertex(-100, -100, 100),
                        Color.BLUE
                ));

                graph2.translate(getWidth() / 2, getHeight() / 2);
                graph2.setColor(Color.WHITE);

                for (Triangle triangle: tris) {
                    Path2D path = new Path2D.Double();
                    path.moveTo(triangle.v1.x, triangle.v1.y);
                    path.lineTo(triangle.v2.x, triangle.v2.y);
                    path.lineTo(triangle.v3.x, triangle.v3.y);
                    path.closePath();
                    graph2.draw(path);
                }
            }
        };
        pane.add(renderPanel, BorderLayout.CENTER);

        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
