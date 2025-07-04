import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ClubSocialAdd {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(name = "dni")
    private WebElement dniInput;

    @FindBy(name = "nombre")
    private WebElement nombreInput;

    @FindBy(name = "apellido")
    private WebElement apellidoInput;

    @FindBy(name = "edad")
    private WebElement edadInput;

    @FindBy(name = "fechaNacimiento")
    private WebElement fechaNacimientoInput;

    @FindBy(name = "direccion")
    private WebElement direccionInput;

    @FindBy(name = "telefono")
    private WebElement telefonoInput;

    @FindBy(css = "button[type='submit']")
    private WebElement botonGuardar;

    @FindBy(id = "mensaje")
    private WebElement mensajeDiv;

    public ClubSocialAdd(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        PageFactory.initElements(driver, this);
    }

    /**
    * Carga los datos de un socio en el formulario del sistema web
    * y simula el clic en el boton "Guardar".
    */
    public void cargarSocio(String dni, String nombre, String apellido, String edad,
                            String fechaNacimiento, String direccion, String telefono) {

        dniInput.sendKeys(dni);
        nombreInput.sendKeys(nombre);
        apellidoInput.sendKeys(apellido);
        edadInput.sendKeys(edad);
        fechaNacimientoInput.sendKeys(fechaNacimiento);
        direccionInput.sendKeys(direccion);
        telefonoInput.sendKeys(telefono);
        botonGuardar.click();
    }

    /**
    * Espera a que aparezca el mensaje de confirmacion indicando que el socio
    * fue agregado exitosamente. Tiene un tiempo lomite de espera.
    */
    public boolean esperarConfirmacion() {
        try {
            return wait.until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOf(mensajeDiv),
                    ExpectedConditions.textToBePresentInElement(mensajeDiv, "Socio agregado con éxito.")
            ));
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**Busca el boton volver al incio
     * */
    public void volverAlInicio() {
        WebElement volverBtn = driver.findElement(By.xpath("//button[contains(text(),'Volver')]"));
        volverBtn.click();

    }
}

