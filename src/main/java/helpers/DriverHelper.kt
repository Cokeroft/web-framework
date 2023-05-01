package helpers

import org.openqa.selenium.*
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.stream.Collectors


// open for use with inheritance to make app specific helpers
open class DriverHelper {
    protected var defaultWaitTime = 20
    var dhs = SelectHelper()
    var chs = CheckBoxHelper()
    var driver = LocalDriverManager.getDriver()
        protected set

    // open in the case to override in app specific implementation
    @JvmOverloads
    open fun click(locator: By, message: String  = "$locator  cannot be clicked" ,waitTime: Int = defaultWaitTime) {
        waitForElementToBeClickable(locator, message)

        WebDriverWait(driver, waitTime.toLong()).until(object : ExpectedCondition<Boolean> {
            override fun apply(webDriver: WebDriver?): Boolean? {
                try {
                    driver.findElement(locator).click()
                } catch (e: WebDriverException) {
                    println("Failed to click locator: $locator")
                    return false
                }

                return true
            }

            override fun toString(): String {
                return message
            }
        })
    }

    @JvmOverloads
    open fun click(xpath: String, message: String = "$xpath  cannot be clicked",waitTime: Int = defaultWaitTime) {
        click(By.xpath(xpath), message,waitTime)
    }

    fun dragDrop(source: By, target: By) {
        waitForElementToExist(source)
        waitForElementToExist(target)
        driver.findElement(source)
        //driver.findElement(source).
    }

    fun waitForElementToPossiblyBeDisplayed(locator: By)
    {
        try {
            waitForElementToBeVisible(locator)
        }catch (e: Exception){

        }
    }

    fun waitForElementToPossiblyBeDisplayed(xpath: String) = waitForElementToPossiblyBeDisplayed(By.xpath(xpath))

    @JvmOverloads
    fun setText(locator: By, text: String,waitTime: Int = defaultWaitTime) {
        waitForElementToExist(locator)
        WebDriverWait(LocalDriverManager.getDriver(), waitTime.toLong()).until(ExpectedConditions.visibilityOfElementLocated(locator))
        driver.findElement(locator).clear()
        driver.findElement(locator).sendKeys(text)
    }

    /**
     * Method to type in a field slower. Selenium tends to break on iFrame when sendKeys is called.
     * It types too fast and misses chars.
     *
     * @param locator
     * @param value
     */
    @JvmOverloads
    fun typeSlowerInField(locator: By, value: String) {
        val element = driver.findElement(locator)
        element.clear()

        for (i in 0 until value.length) {
            val c = value[i]
            val s = StringBuilder().append(c).toString()
            driver.findElement(locator).sendKeys(s)
            try {
                Thread.sleep(250)
            } catch (e: java.lang.InterruptedException) {
            }
        }
    }

    @JvmOverloads
    fun setText(xpath: String, text: String,waitTime: Int = defaultWaitTime) = setText(By.xpath(xpath), text, waitTime)

    open fun getText(locator: By): String {
        waitForElementToExist(locator)
        return driver.findElement(locator).text
    }

    fun getText(xpath: String): String =  getText(By.xpath(xpath))

    fun getValue(by: By): String = getAttribute(by, "value")


    fun getAttribute(by: By, attribute: String): String = driver.findElement(by).getAttribute(attribute)


    fun getURL(url: String) = driver.get(url)


    fun findElements(xpath: String): List<WebElement> = findElements(By.xpath(xpath))


    fun findElements(locator: By): List<WebElement> = driver.findElements(locator)


    fun findElement(locator: By): WebElement = driver.findElement(locator)


    fun findElement(xpath: String): WebElement = findElement(By.xpath(xpath))


    fun isSelected(locator: By): Boolean {
        waitForElementToExist(locator)
        return driver.findElement(locator).isSelected
    }

    fun isDisplayed(by: By): Boolean {
        val elements = driver.findElements(by)
        try {
            return !elements.isEmpty() && elements[0].isDisplayed
        } catch (e: StaleElementReferenceException) {
            return false
        }
    }

    fun isSelected(xpath: String): Boolean = isSelected(By.xpath(xpath))


    fun isClickable(locator: By): Boolean {
        waitForElementToExist(locator)
        return driver.findElement(locator).isEnabled
    }

    fun tab() = driver.findElement(By.tagName("body")).sendKeys(Keys.TAB)

    @JvmOverloads
    fun waitForElementToBeClickable(xpath: String,message: String = "Waited for element to be clickable at locator: '$xpath'",waitTime: Int = defaultWaitTime)
            = waitForElementToBeClickable(By.xpath(xpath),message,waitTime)

    @JvmOverloads
    fun waitForElementToBeClickable(locator: By,message: String= "Waited for element to be clickable at locator: '$locator'",  waitTime: Int = defaultWaitTime)
            = WebDriverWait(driver, waitTime.toLong()).until(object : ExpectedCondition<WebElement> {
        override fun apply(webDriver: WebDriver?): WebElement? {
            return ExpectedConditions.elementToBeClickable(locator).apply(driver)
        }

        override fun toString(): String {
            return message
        }
    })

    @JvmOverloads
    fun waitForElementToBeVisible(xpath: String,waitTime: Int = defaultWaitTime) = waitForElementToBeVisible(By.xpath(xpath),waitTime)


    @JvmOverloads
    fun waitForElementToBeVisible(locator: By, waitTime: Int = defaultWaitTime) =
        WebDriverWait(driver, waitTime.toLong()).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator))

    @JvmOverloads
    fun waitForElementToNotBeVisible(locator: By, waitTime: Int = defaultWaitTime) {
        WebDriverWait(driver, waitTime.toLong()).until(ExpectedConditions.invisibilityOfElementLocated(locator))
    }

    @JvmOverloads
    fun waitForElementToExist(xpath: String, message: String = "element to exist. Locator: '$xpath'", waitTime: Int = defaultWaitTime) =
        waitForElementToExist(By.xpath(xpath),message, waitTime)

    @JvmOverloads
    fun waitForElementToExist(locator: By,message: String = "element to exist. Locator: '$locator'", waitTime: Int = defaultWaitTime) {
        WebDriverWait(driver, waitTime.toLong()).until(object : ExpectedCondition<Boolean> {
            override fun apply(webDriver: WebDriver?): Boolean? {
                return elementExists(locator)
            }

            override fun toString(): String {
                return message
            }
        })
    }

    fun elementExists(locator: By): Boolean = driver.findElements(locator).size > 0

    @JvmOverloads
    fun waitForTextToBe(locator: By, expectedText: String, message: String = "\"text at locator: '$locator' to be '$expectedText'.\"") {
        WebDriverWait(driver, 15).until(object : ExpectedCondition<Boolean> {
            override fun apply(webDriver: WebDriver?): Boolean? {
                return ExpectedConditions.textToBe(locator, expectedText).apply(driver)
            }

            override fun toString(): String {
                return message
            }
        })
    }

    @JvmOverloads
    fun waitForTextNotToBe(locator: By, unexpectedText: String,waitTime: Int = defaultWaitTime) {
        WebDriverWait(driver, waitTime.toLong()).until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, unexpectedText)))
    }
    @JvmOverloads
    fun waitForTextNotToBe(xpath: String, unexpectedText: String, waitTime: Int = defaultWaitTime) = waitForTextNotToBe(By.xpath(xpath), unexpectedText,waitTime)

    @JvmOverloads
    fun waitForUrlToBe(url: String,waitTime: Int = defaultWaitTime ) = WebDriverWait(driver, waitTime.toLong()).until(ExpectedConditions.urlToBe(url))

    @JvmOverloads
    fun waitForUrlToContain(url: String,message: String= "waiting for url to contain '$url' but found '${driver.currentUrl}'", waitTime: Int = defaultWaitTime) {
        WebDriverWait(driver, waitTime.toLong()).until(object : ExpectedCondition<Boolean> {
            override fun apply(webDriver: WebDriver?): Boolean? {
                return driver.currentUrl.contains(url)
            }

            override fun toString(): String {
                return message
            }
        })
    }

    //TODO Find better way to denote failure (receives a TimeoutException)

    //endregion

    fun scrollToTopOfPage() = (driver as JavascriptExecutor).executeScript("window.scrollTo(0,0)")

    fun scrollToBottomOfPage() = (driver as JavascriptExecutor).executeScript("window.scrollTo(0, document.body.scrollHeight)")

    fun scrollToElement(selector: By) {
        waitForElementToExist(selector);
        scrollToElement(this.findElement(selector));
    }

    fun scrollToElement(element: WebElement) {
        val midHeight = driver.manage().window().size.height/2
        val y = element.rect.y - midHeight;
        (driver as JavascriptExecutor).executeScript("window.scrollTo(0,$y)")
    }


    inner class CheckBoxHelper {
        fun isChecked(by : By): Boolean {
            waitForElementToBeClickable(by)
            return findElement(by).isSelected
        }

        fun isChecked(xpath : String): Boolean = isChecked(By.xpath(xpath))


        fun checkElement(by : By) {
            if (!isChecked(by))
                click(by)

            assert(isChecked(by))
        }

        fun checkElement(xpath : String) = checkElement(By.xpath(xpath))

        fun uncheckElement(by : By) {
            if (isChecked(by))
                click(by)

            assert(!isChecked(by))
        }

        fun uncheckElement(xpath : String) = uncheckElement(By.xpath(xpath))
    }

    inner class SelectHelper {

        // Functions here kept separate for ease of maintenance and readability
        fun selectOption(locator: By, option: String) {
            WebDriverWait(driver, defaultWaitTime.toLong()).until(object : ExpectedCondition<Boolean> {
                override fun apply(webDriver: WebDriver?): Boolean? {
                    try {
                        Select(driver.findElement(locator)).selectByVisibleText(option)
                    } catch (e: WebDriverException) {
                        return false
                    }

                    return true
                }

                override fun toString(): String {
                    var options = Select(driver.findElement(locator)).options.stream().map(WebElement::getText).collect(Collectors.toList())
                    return "option to appear: '$option'\nFound the following options: {$options}"
                }
            })
        }

        fun selectOption(xpath: String, option: String) = selectOption(By.xpath(xpath), option)

        fun selectOption(locator: By, index: Int) {
            val select = Select(driver.findElement(locator))
            WebDriverWait(driver, defaultWaitTime.toLong()).until(object : ExpectedCondition<Boolean> {
                override fun apply(webDriver: WebDriver?): Boolean? {
                    try {
                        select.selectByIndex(index)
                    } catch (e: NoSuchElementException) {
                        return false
                    }

                    return true
                }

                override fun toString(): String {
                    return "Could Not Find the option by index\nIndex Given: $index \nNumber of elements found:  ${select.options.size}"
                }
            })
        }

        fun selectOption(xpath: String, index: Int) = selectOption(By.xpath(xpath), index)

        fun getOptionsText(locator: By): List<String> {
            val select = Select(driver.findElement(locator))
            return select.options.stream().map(WebElement::getText).collect(Collectors.toList())
        }
        fun getOptions(locator: By): List<WebElement> {
            val select = Select(findElement(locator))
            return select.options
        }

        fun getFirstSelectedOptionText(locator: By): String = Select(findElement(locator)).firstSelectedOption.text

        fun getFirstSelectedOptionText(xpath: String): String = getFirstSelectedOptionText(By.xpath(xpath))
    }

    // Method to navigate to a URL and functionality of resizing the browser based on URL
    // This doesn't work though'
    /**fun navigateToURL(URL: String) {
        driver.navigate().to(URL)
        // Resize page similar to a mobile phone if URL is a mobile version
        when {
            URL.contains("://m.") -> {
                driver.manage().window().position = Point(0, 0)
                driver . manage ().window().size = Dimension(400, 811)
            }
            else -> {
                driver.manage().window().maximize()
            }
        }
    }*/

    fun getCookieInfo(): Set<Cookie> {
        return driver.manage().getCookies()
    }

    fun getCurrentUrl(): String {
        return driver.currentUrl
    }

    fun refreshBrowser() {
        driver.navigate().refresh()
    }
}