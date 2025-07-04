import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ClubSocialComprobarAdd {

    private final WebDriverWait wait;

    public ClubSocialComprobarAdd(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    /**
    * Busca un socio en la interfaz web utilizando su DNI.
    * Rellena el campo de busqueda y simula el clic en el boton "Buscar".
    */
    public void buscarSocio(String dni) {
        WebElement dniBuscarInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dniBuscar")));
        WebElement botonBuscar = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnBuscar")));

        dniBuscarInput.clear();
        dniBuscarInput.sendKeys(dni);
        botonBuscar.click();
    }

    /**
    * Verifica si el resultado de la busqueda contiene el DNI esperado.
    * Extrae el texto del div de resultado y lo compara buscando que sea el mismo.
    */
    public boolean verificarSocioAparecePorDni(String dniEsperado) {
        WebElement resultadoDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultadoSocio")));
        String texto = resultadoDiv.getText().toLowerCase();
        return texto.contains(dniEsperado.toLowerCase());
    }
}
