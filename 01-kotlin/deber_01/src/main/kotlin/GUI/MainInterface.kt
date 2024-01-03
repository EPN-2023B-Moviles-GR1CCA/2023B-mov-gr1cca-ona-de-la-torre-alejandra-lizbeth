package GUI

import java.awt.Color
import java.awt.Font
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import javax.swing.*

class MainInterface(): JFrame() {
    var mainPanel:JPanel ?= null

    init{
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        agregarPanel()
        agregarEtiquetas()
        agregarBoton()
    }

    fun agregarPanel(){
        var contenedor = JPanel()
        contenedor.background = Color(6,39,40)
        this.contentPane.add(contenedor)

        mainPanel = JPanel()
        contenedor.add(mainPanel)
        mainPanel!!.background = Color(6,39,40)
        contenedor.layout = BoxLayout(contenedor, BoxLayout.X_AXIS)
    }

    fun agregarEtiquetas(){
        var lblTitle = JLabel("Aplicación relación cocinero y comidas")
        var lblAutor = JLabel("Alejandra Oña")
        var lblVersion = JLabel("Version 1.0")
        mainPanel!!.add(lblTitle)
        mainPanel!!.setLayout(null)
        lblTitle.setBounds(60,10,260,60)
        lblTitle.horizontalAlignment = 0 //centrado horizontalmente
        lblTitle.foreground = Color.white

        mainPanel!!.add(lblAutor)
        lblAutor.setBounds(280,200,80,60)
        lblAutor.foreground = Color.white
        lblAutor.horizontalAlignment = 4 //alineado derecho

        mainPanel!!.add(lblVersion)
        lblVersion.setBounds(20,200,70,60)
        lblVersion.foreground = Color.white
        lblVersion.horizontalAlignment = 2 //alineado izquierdo

        /*Fuentes*/
        lblTitle.font = Font("Times New Roman", Font.BOLD, 14)
        lblAutor.font = Font("Times New Roman", Font.PLAIN, 11)
        lblVersion.font = Font("Times New Roman", Font.PLAIN, 11)
    }

    fun agregarBoton(){
        var btnVerCocineros = JButton("Ver cocineros")
        mainPanel!!.add(btnVerCocineros)
        mainPanel!!.setLayout(null)
        btnVerCocineros.setBounds(130,110,130,30)
        btnVerCocineros.background = Color(236,225,195)
        btnVerCocineros.mnemonic = KeyEvent.VK_ENTER
        btnVerCocineros.font = Font("Times New Roman", Font.BOLD, 14)

        // Agregar ActionListener para el botón "Ver cocineros"
        btnVerCocineros.addActionListener(object : ActionListener {
            override fun actionPerformed(e: ActionEvent) {
                // Lógica para ocultar esta ventana y mostrar Cocineros
                isVisible = false
                CocineroInterface().isVisible = true
            }
        })
    }
}