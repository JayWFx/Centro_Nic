package ni.uam.edu.centronic.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ni.uam.edu.centronic.DAO.EstudianteDAO;
import ni.uam.edu.centronic.modelos.Estudiante;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MatriculaController {

    private final EstudianteDAO dao = new EstudianteDAO();
    private final ObservableList<Estudiante> datosTabla = FXCollections.observableArrayList();

    @FXML private TextField txtNombres;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;
    @FXML private DatePicker dpFechaNacimiento;
    @FXML private ComboBox<String> cmbDepartamento;
    @FXML private ListView<String> lvCursos;
    @FXML private RadioButton rbPresencial;
    @FXML private RadioButton rbVirtual;
    @FXML private CheckBox chkMatutino;
    @FXML private CheckBox chkVespertino;
    @FXML private CheckBox chkSabatino;
    @FXML private CheckBox chkAceptaNormas;
    @FXML private Label lblTotalRegistros;

    @FXML private TableView<Estudiante> tblEstudiantes;
    @FXML private TableColumn<Estudiante, String> colNombreCompleto;
    @FXML private TableColumn<Estudiante, String> colDepartamento;
    @FXML private TableColumn<Estudiante, String> colCurso;
    @FXML private TableColumn<Estudiante, String> colModalidad;
    @FXML private TableColumn<Estudiante, String> colHorario;
    @FXML private TableColumn<Estudiante, String> colFechaNac;

    private ToggleGroup tgModalidad;

    @FXML
    public void initialize() {
        inicializarDepartamentos();
        inicializarCursos();
        configurarModalidad();
        configurarTabla();
    }

    private void inicializarDepartamentos() {
        cmbDepartamento.getItems().addAll(
                "Managua", "León", "Granada", "Masaya",
                "Matagalpa", "Estelí", "Chinandega", "Carazo",
                "Rivas", "Chontales"
        );
    }

    private void inicializarCursos() {
        lvCursos.getItems().addAll(
                "Programación",
                "Excel",
                "Redes",
                "Diseño Gráfico"
        );
    }

    private void configurarModalidad() {
        tgModalidad = new ToggleGroup();
        rbPresencial.setToggleGroup(tgModalidad);
        rbVirtual.setToggleGroup(tgModalidad);
    }

    private void configurarTabla() {
        colNombreCompleto.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getNombreCompleto()));
        colDepartamento.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getDepartamento()));
        colCurso.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getCurso()));
        colModalidad.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getModalidad()));
        colHorario.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getHorario()));
        colFechaNac.setCellValueFactory(f -> {
            LocalDate fecha = f.getValue().getFechaNacimiento();
            return new SimpleStringProperty(fecha == null ? "" : fecha.toString());
        });

        tblEstudiantes.setItems(datosTabla);
    }

    @FXML
    protected void registrarOnClick() {
        if (!validarFormulario()) {
            return;
        }

        String nombres = txtNombres.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String usuario = txtUsuario.getText().trim();
        String password = txtPassword.getText();
        LocalDate fechaNac = dpFechaNacimiento.getValue();
        String departamento = cmbDepartamento.getValue();
        String curso = lvCursos.getSelectionModel().getSelectedItem();
        RadioButton rbSeleccionado = (RadioButton) tgModalidad.getSelectedToggle();
        String modalidad = rbSeleccionado.getText();
        String horario = obtenerHorariosSeleccionados();
        boolean normas = chkAceptaNormas.isSelected();

        if (dao.existeUsuario(usuario)) {
            mostrarAlerta(Alert.AlertType.ERROR, "Usuario existente", "El nombre de usuario ya está registrado en el sistema.");
            return;
        }

        Estudiante nuevoEstudiante = new Estudiante(
                nombres,
                apellidos,
                usuario,
                password,
                fechaNac,
                departamento,
                curso,
                modalidad,
                horario,
                normas
        );

        dao.agregar(nuevoEstudiante);
        datosTabla.setAll(dao.obtenerRegistros());
        actualizarContador();
        limpiarCampos();

        mostrarAlerta(Alert.AlertType.INFORMATION, "Matrícula Exitosa", "Estudiante matriculado con éxito en el sistema.");
    }

    @FXML
    protected void limpiarOnClick() {
        limpiarCampos();
    }

    private boolean validarFormulario() {
        if (!validarTexto(txtNombres)) {
            mostrarAlerta(Alert.AlertType.ERROR, "Campo vacío", "Debe ingresar los nombres del estudiante.");
            return false;
        }

        if (!validarTexto(txtApellidos)) {
            mostrarAlerta(Alert.AlertType.ERROR, "Campo vacío", "Debe ingresar los apellidos del estudiante.");
            return false;
        }

        if (!validarTexto(txtUsuario)) {
            mostrarAlerta(Alert.AlertType.ERROR, "Campo vacío", "Debe ingresar un nombre de usuario.");
            return false;
        }

        if (txtUsuario.getText().trim().length() < 5) {
            mostrarAlerta(Alert.AlertType.ERROR, "Longitud inválida", "El usuario debe tener al menos 5 caracteres.");
            return false;
        }

        if (txtPassword.getText() == null || txtPassword.getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Campo vacío", "Debe ingresar una contraseña.");
            return false;
        }

        if (txtPassword.getText().length() < 8) {
            mostrarAlerta(Alert.AlertType.ERROR, "Longitud inválida", "La contraseña debe tener al menos 8 caracteres.");
            return false;
        }

        if (dpFechaNacimiento.getValue() == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Fecha no seleccionada", "Debe seleccionar la fecha de nacimiento.");
            return false;
        }

        if (cmbDepartamento.getValue() == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Departamento no seleccionado", "Debe seleccionar un departamento.");
            return false;
        }

        if (lvCursos.getSelectionModel().getSelectedItem() == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Curso no seleccionado", "Debe elegir un curso de la lista.");
            return false;
        }

        if (tgModalidad.getSelectedToggle() == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Modalidad no seleccionada", "Debe elegir la modalidad (Presencial o Virtual).");
            return false;
        }

        if (!hayHorarioSeleccionado()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Horario no seleccionado", "Debe seleccionar al menos un horario preferido.");
            return false;
        }

        if (!chkAceptaNormas.isSelected()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Normas no aceptadas", "Debe aceptar las normas y reglamento del centro para continuar.");
            return false;
        }

        return true;
    }

    private boolean validarTexto(TextField campo) {
        return campo.getText() != null && !campo.getText().trim().isEmpty();
    }

    private boolean hayHorarioSeleccionado() {
        return chkMatutino.isSelected() || chkVespertino.isSelected() || chkSabatino.isSelected();
    }

    private String obtenerHorariosSeleccionados() {
        List<String> horarios = new ArrayList<>();
        if (chkMatutino.isSelected()) horarios.add("Matutino");
        if (chkVespertino.isSelected()) horarios.add("Vespertino");
        if (chkSabatino.isSelected()) horarios.add("Sabatino");
        return String.join(", ", horarios);
    }

    private void actualizarContador() {
        lblTotalRegistros.setText("Total matriculados: " + dao.obtenerRegistros().size());
    }

    private void limpiarCampos() {
        txtNombres.clear();
        txtApellidos.clear();
        txtUsuario.clear();
        txtPassword.clear();
        dpFechaNacimiento.setValue(null);
        cmbDepartamento.getSelectionModel().clearSelection();
        lvCursos.getSelectionModel().clearSelection();
        if (tgModalidad.getSelectedToggle() != null) {
            tgModalidad.getSelectedToggle().setSelected(false);
        }
        chkMatutino.setSelected(false);
        chkVespertino.setSelected(false);
        chkSabatino.setSelected(false);
        chkAceptaNormas.setSelected(false);
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}