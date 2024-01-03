package GUI

import java.awt.Color
import java.awt.Font
import java.awt.Image
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.*

class EliminarCocineroInterface(numeroUnico: String): JFrame() {

    var mainPanel: JPanel?= null

    init {
        defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        title = "Aplicación cocinero comidas"
        contentPane.background = Color(255, 255, 255)
        setSize(400, 180)
        setLocation(600,280)
        isResizable = false
        // Agregar un WindowListener para manejar el evento de cierre de la ventana
        addWindowListener(object : WindowAdapter() {
            override fun windowClosed(e: WindowEvent) {
                // Lógica para volver a abrir MainInterface cuando se cierre CocineroInterface
                CocineroInterface().isVisible = true
            }
        })

        agregarPanel()
        agregarEtiquetas()
        onAceptar(numeroUnico)
        onNegar()
    }

    fun agregarPanel(){
        var contenedor = JPanel()
        contenedor.background = Color(25,94,99)
        this.contentPane.add(contenedor)

        mainPanel = JPanel()
        contenedor.add(mainPanel)
        mainPanel!!.background = Color(25,94,99)
        contenedor.layout = BoxLayout(contenedor, BoxLayout.X_AXIS)
    }

    fun agregarEtiquetas(){
        var lblTitle = JLabel("¿Está seguro de eliminar al cocinero?")
        mainPanel!!.add(lblTitle)
        mainPanel!!.setLayout(null)
        lblTitle.setBounds(20,20,350,40)
        lblTitle.horizontalAlignment = 0 //centrado horizontalmente
        lblTitle.foreground = Color.white
        lblTitle.font = Font("Times New Roman", Font.BOLD, 20)
    }

    fun onAceptar(numCocinero: String){
        var btnAceptar = JButton("Sí")
        mainPanel!!.add(btnAceptar)
        mainPanel!!.setLayout(null)
        btnAceptar.setBounds(130, 80, 60, 30)

        btnAceptar.addActionListener(object : ActionListener {
            override fun actionPerformed(e: ActionEvent) {
                isVisible = false
            }
        })
    }

    fun onNegar(){
        var btnNegar = JButton("No")
        mainPanel!!.add(btnNegar)
        mainPanel!!.setLayout(null)
        btnNegar.setBounds(210, 80, 60, 30)

        btnNegar.addActionListener(object : ActionListener {
            override fun actionPerformed(e: ActionEvent) {
                isVisible = false
            }
        })
    }
}