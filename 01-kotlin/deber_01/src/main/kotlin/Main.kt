
import GUI.MainInterface
import javax.swing.JFrame
import javax.swing.SwingUtilities

fun main() {
    SwingUtilities.invokeLater {
        val frame: JFrame = MainInterface()
        frame.title = "Aplicación cocinero comidas"
        frame.setLocation(600,250)
        frame.setSize(400, 300)
        frame.isResizable = false
        frame.isVisible = true
    }
}