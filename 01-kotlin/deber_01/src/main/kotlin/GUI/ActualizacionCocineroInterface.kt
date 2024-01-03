package GUI

import Model.Cocinero
import Repository.CocineroRepository
import Repository.ComidaRepository
import java.awt.Color
import java.awt.Font
import java.awt.Image
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.util.*
import javax.swing.*

class ActualizacionCocineroInterface ( numeroUnico: String): JFrame() {
    val cocineroRepository: CocineroRepository = CocineroRepository("src/data")
    val comidaRepository: ComidaRepository = ComidaRepository()
    var mainPanel: JPanel ?= null
    var txtCodigoUnico: JTextField ?= null
    var txtNombres: JTextField ?= null
    var txtApellidos: JTextField ?= null
    var txtEdad: JTextField ?= null
    var txtFechaContratacion: JTextField ?= null
    var txtSalario: JTextField ?= null
    var txtEsChefPrincipal: JTextField ?= null

    val uniqueCode = numeroUnico;

    init {
        defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        title = "Aplicación cocinero comidas"
        contentPane.background = Color(255, 255, 255)
        setSize(800, 500)
        setLocation(400,80)
        isResizable = false
        // Agregar un WindowListener para manejar el evento de cierre de la ventana
        addWindowListener(object : WindowAdapter() {
            override fun windowClosed(e: WindowEvent) {
                CocineroInterface().isVisible = true
            }
        })

        agregarPanel()
        volverCocineroInterface()
        agregarEtiquetas()
        agregarCampoTexto()
        agregarCocinero()
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
        var lblTitle = JLabel("Bienvenido")
        var lblTitle2 = JLabel("Ingresa los datos del cocinero")
        var lblDatosPersonales = JLabel("Datos personales")
        var lblCodigoUnico = JLabel("Código único: ")
        var lblNombres = JLabel("Nombres: ")
        var lblApellidos = JLabel("Apellidos: ")
        var lblEdad = JLabel("Edad: ")
        var lblDatosContratacion = JLabel("Datos de contratación")
        var lblFechaContratacion = JLabel("Fecha de contratación: ")
        var lblSalario = JLabel("Salario: ")
        var lblEsChefPrincipal = JLabel("¿Es chef principal? ")
        mainPanel!!.add(lblTitle)
        mainPanel!!.add(lblTitle2)
        mainPanel!!.add(lblDatosPersonales)
        mainPanel!!.add(lblCodigoUnico)
        mainPanel!!.add(lblNombres)
        mainPanel!!.add(lblApellidos)
        mainPanel!!.add(lblEdad)
        mainPanel!!.add(lblDatosContratacion)
        mainPanel!!.add(lblFechaContratacion)
        mainPanel!!.add(lblSalario)
        mainPanel!!.add(lblEsChefPrincipal)
        mainPanel!!.setLayout(null)
        lblTitle.setBounds(325,10,150,40)
        lblTitle.horizontalAlignment = 0 //centrado horizontalmente
        lblTitle.foreground = Color(236,225,195)
        lblTitle.font = Font("Times New Roman", Font.BOLD, 25)

        lblTitle2.setBounds(250,60,300,30)
        lblTitle2.horizontalAlignment = 0 //centrado horizontalmente
        lblTitle2.foreground = Color(236,225,195)
        lblTitle2.font = Font("Times New Roman", Font.BOLD, 20)

        lblDatosPersonales.setBounds(40,100,120,30)
        lblDatosPersonales.horizontalAlignment = 2 //alineado izquierdo
        lblDatosPersonales.foreground = Color(236,225,195)
        lblDatosPersonales.font = Font("Times New Roman", Font.BOLD, 16)

        lblCodigoUnico.setBounds(40,140,100,30)
        lblCodigoUnico.horizontalAlignment = 2
        lblCodigoUnico.foreground = Color.white
        lblCodigoUnico.font = Font("Times New Roman", Font.BOLD, 14)

        lblNombres.setBounds(40,170,70,30)
        lblNombres.horizontalAlignment = 2
        lblNombres.foreground = Color.white
        lblNombres.font = Font("Times New Roman", Font.BOLD, 14)

        lblApellidos.setBounds(340,170,70,30)
        lblApellidos.horizontalAlignment = 2
        lblApellidos.foreground = Color.white
        lblApellidos.font = Font("Times New Roman", Font.BOLD, 14)

        lblEdad.setBounds(40,200,70,30)
        lblEdad.horizontalAlignment = 2
        lblEdad.foreground = Color.white
        lblEdad.font = Font("Times New Roman", Font.BOLD, 14)

        lblDatosContratacion.setBounds(40,240,160,30)
        lblDatosContratacion.horizontalAlignment = 2
        lblDatosContratacion.foreground = Color(236,225,195)
        lblDatosContratacion.font = Font("Times New Roman", Font.BOLD, 16)

        lblFechaContratacion.setBounds(40,280,180,30)
        lblFechaContratacion.horizontalAlignment = 2
        lblFechaContratacion.foreground = Color.white
        lblFechaContratacion.font = Font("Times New Roman", Font.BOLD, 14)

        lblSalario.setBounds(420,280,70,30)
        lblSalario.horizontalAlignment = 2
        lblSalario.foreground = Color.white
        lblSalario.font = Font("Times New Roman", Font.BOLD, 14)

        lblEsChefPrincipal.setBounds(40,310,180,30)
        lblEsChefPrincipal.horizontalAlignment = 2
        lblEsChefPrincipal.foreground = Color.white
        lblEsChefPrincipal.font = Font("Times New Roman", Font.BOLD, 14)
    }

    fun agregarCampoTexto(){
        txtCodigoUnico = JTextField()
        txtNombres = JTextField()
        txtApellidos = JTextField()
        txtEdad = JTextField()
        txtFechaContratacion = JTextField()
        txtSalario = JTextField()
        txtEsChefPrincipal = JTextField()
        mainPanel!!.add(txtCodigoUnico)
        mainPanel!!.add(txtNombres)
        mainPanel!!.add(txtApellidos)
        mainPanel!!.add(txtEdad)
        mainPanel!!.add(txtFechaContratacion)
        mainPanel!!.add(txtSalario)
        mainPanel!!.add(txtEsChefPrincipal)
        mainPanel!!.setLayout(null)
        txtCodigoUnico!!.setBounds(160,140,150,20)
        txtCodigoUnico!!.foreground = Color.BLACK
//        txtCodigoUnico.background = Color(142,189,182)
        txtCodigoUnico!!.horizontalAlignment = 0
        txtCodigoUnico!!.font = Font("Times New Roman", Font.PLAIN, 13)

        txtNombres!!.setBounds(130,170,200,20)
        txtNombres!!.foreground = Color.BLACK
        txtNombres!!.horizontalAlignment = 0
        txtNombres!!.font = Font("Times New Roman", Font.PLAIN, 13)

        txtApellidos!!.setBounds(430,170,210,20)
        txtApellidos!!.foreground = Color.BLACK
        txtApellidos!!.horizontalAlignment = 0
        txtApellidos!!.font = Font("Times New Roman", Font.PLAIN, 13)

        txtEdad!!.setBounds(130,200,50,20)
        txtEdad!!.foreground = Color.BLACK
        txtEdad!!.horizontalAlignment = 0
        txtEdad!!.font = Font("Times New Roman", Font.PLAIN, 13)

        txtFechaContratacion!!.setBounds(240,280,160,20)
        txtFechaContratacion!!.foreground = Color.BLACK
        txtFechaContratacion!!.horizontalAlignment = 0
        txtFechaContratacion!!.font = Font("Times New Roman", Font.PLAIN, 13)

        txtSalario!!.setBounds(510,280,100,20)
        txtSalario!!.foreground = Color.BLACK
        txtSalario!!.horizontalAlignment = 0
        txtSalario!!.font = Font("Times New Roman", Font.PLAIN, 13)

        txtEsChefPrincipal!!.setBounds(240,310,50,20)
        txtEsChefPrincipal!!.foreground = Color.BLACK
        txtEsChefPrincipal!!.horizontalAlignment = 0
        txtEsChefPrincipal!!.font = Font("Times New Roman", Font.PLAIN, 13)

        llenarCampos()
    }

    fun llenarCampos(){
        val chef: Cocinero ?= cocineroRepository.getChefByCodigoUnico(uniqueCode)
        println(cocineroRepository.getChefs())
        if(chef !== null){
            txtCodigoUnico!!.setText(uniqueCode)
            txtNombres!!.setText(chef!!.nombre)
            txtApellidos!!.setText(chef.apellido)
            txtEdad!!.setText(chef.edad.toString())
            txtFechaContratacion!!.setText(chef.fechaContratacion.toString())
            txtSalario!!.setText(chef.salario.toString())
            txtEsChefPrincipal!!.setText(
                if(chef.isMainChef) "Si"  else "No"
            )
        }

    }

    fun volverCocineroInterface(){
        var btnVolver = JButton()
        mainPanel!!.add(btnVolver)
        mainPanel!!.setLayout(null)
        btnVolver.setBounds(20,20,30,20)
        btnVolver.isOpaque = false

        btnVolver.icon = ImageIcon(ImageIcon("src/images/flecha-izquierda.png").image.getScaledInstance(30,20, Image.SCALE_FAST))
        // Agregar ActionListener para el botón "volver"
        btnVolver.addActionListener(object : ActionListener {
            override fun actionPerformed(e: ActionEvent) {
                isVisible = false
                CocineroInterface().isVisible = true
            }
        })
    }

    fun agregarCocinero(){
        var btnAgregarCocinero = JButton("Guardar")
        mainPanel!!.add(btnAgregarCocinero)
        mainPanel!!.setLayout(null)
        btnAgregarCocinero.setBounds(325,370,150,25)
        btnAgregarCocinero.background = Color(236,225,195)
        btnAgregarCocinero.font = Font("Times New Roman", Font.BOLD, 14)

        btnAgregarCocinero.addActionListener(object : ActionListener {
            override fun actionPerformed(e: ActionEvent) {

                val codigoUnico: String = txtCodigoUnico!!.getText()
                val nombres: String = txtNombres!!.getText()
                val apellidos: String = txtApellidos!!.getText()
                val edad: Int = txtEdad!!.getText().toInt()
                val fechaContratacion: Date = Date(txtFechaContratacion!!.getText())
                val salario: Double = txtSalario!!.getText().toDouble()
                val isMainChef: Boolean = txtEsChefPrincipal!!.getText().equals("Si")

                val newChef: Cocinero = Cocinero(
                    codigoUnico, nombres, apellidos, edad, fechaContratacion, salario, isMainChef
                )

                println(newChef.fechaContratacion)
                println(newChef.edad)
                println(newChef.codigoUnico)
                println(newChef.isMainChef)
                println(newChef.salario)

                limpiarCampos()

//                isVisible = false
//                CocineroInterface().isVisible = true
            }
        })
    }

    fun limpiarCampos(){
        txtCodigoUnico!!.setText("")
        txtNombres!!.setText("")
        txtApellidos!!.setText("")
        txtEdad!!.setText(null)
        txtFechaContratacion!!.setText(null)
        txtSalario!!.setText(null)
        txtEsChefPrincipal!!.setText(null)
    }
}