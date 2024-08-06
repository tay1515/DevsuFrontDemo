Feature: Carrito de compras

  Scenario: Carrito de compras
    Given Ingreso a la pagina saucedemo
    When Intento iniciar sesion con las credenciales
      | user          | password     |
      | standard_user | secret_sauce |
    And Intento agregar 2 productos al carrito
    And Ingreso informacion personal
      | Nombre | Apellido | Codigo Postal |
      | Andres | Zapata   | 050033        |
    And Finalizo la compra
    Then El sistema debe de mostrame el mensaje "Thank you for your order!"