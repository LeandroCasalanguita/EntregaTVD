import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;

public class ClubSocialTest {

    private WebDriver driver;
    private ClubSocialAdd cargar;
    private ClubSocialComprobarAdd comprobar;


    @BeforeClass
    public void initPageFactory() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        cargar = PageFactory.initElements(driver,ClubSocialAdd.class);
        comprobar = PageFactory.initElements(driver,ClubSocialComprobarAdd.class);

    }

    @Test(priority=1)
    public void cargarPagina() {
        driver.navigate().to("http://127.0.0.1:5500/index.html");
        Assert.assertEquals(driver.getTitle(), "Club Social");
    }

    @DataProvider(name = "sociosProvider")
    public Object[][] datosSocios() throws IOException {
        return CsvLector.leerCSV("src/main/resources/socios.csv");
    }

    /**
    * Inserta un nuevo socio en el sistema utilizando los datos provistos por el DataProvider.
    * Simula la accion del usuario en la interfaz web: clic en "Agregar Socio", carga del formulario,
    * espera por confirmación del mensaje de exito, y regreso a la pantalla principal.
    * */
    @Test(priority = 2, dataProvider = "sociosProvider")
    public void insertarElemento(String dni, String nombre, String apellido, String edad, String fechaNacimiento, String direccion, String telefono) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        WebElement btnAgregar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[1]/button[1]")));
        btnAgregar.click();
        cargar.cargarSocio(dni, nombre, apellido, edad, fechaNacimiento, direccion, telefono);


        boolean ok = cargar.esperarConfirmacion();
        Assert.assertTrue(ok);

        cargar.volverAlInicio();

    }

    /**
    * Verifica que un socio previamente insertado se encuentre en el sistema.
    * Simula la busqueda del socio por su DNI, valida que aparezca en los resultados
    * y vuelve a la pantalla principal si es encontrado.
    */
    @Test(priority = 3, dataProvider = "sociosProvider")
    public void comprobarSocioInsertado(String dni, String nombre, String apellido, String edad, String fechaNacimiento, String direccion, String telefono) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement btnBuscarSocio = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Buscar por DNI')]")));
        btnBuscarSocio.click();

        comprobar.buscarSocio(dni);

        boolean encontrado = comprobar.verificarSocioAparecePorDni(dni);
        Assert.assertTrue(encontrado, "No se encontró el socio con DNI: " + dni);

        if (encontrado) cargar.volverAlInicio();
    }

    @AfterClass
    public void cerrarNavegador() {
        if (driver != null) {
            driver.quit();
        }
    }
}
