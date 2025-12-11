package com.colonial.web.controller;

import com.colonial.domain.enums.TransactionType;
import com.colonial.domain.model.Transaction;
import com.colonial.domain.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transacciones", description = "Gestión completa de transacciones de ventas y compras del sistema")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Operation(
            summary = "Obtener todas las transacciones",
            description = """
                Devuelve un listado completo de todas las transacciones registradas en el sistema.
                
                **¿Qué incluye?**
                - Ventas (SALE)
                - Compras (PURCHASE)
                - Información completa de cada transacción con sus items
                
                **Uso recomendado:** Para ver el historial completo o generar reportes generales.
                """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de transacciones obtenida exitosamente")
            }
    )
    @GetMapping("/all")
    public ResponseEntity<List<Transaction>> getAll() {
        return ResponseEntity.ok(transactionService.findAll());
    }

    @Operation(
            summary = "Crear una nueva transacción",
            description = """   
                Registra una nueva transacción en el sistema. Puede ser una **VENTA** o una **COMPRA**.
                
                **Importante:**
                - Solo necesitas enviar la información básica
                - El sistema automáticamente busca los productos y usuarios por sus IDs
                - El stock se actualiza automáticamente según el tipo de transacción
                
                **Ejemplo de una VENTA:**
                ```json
                {
                  "idUser": 2,
                  "type": "SALE",
                  "items": [
                    {
                      "idProduct": 4,
                      "quantity": 10
                    },
                    {
                      "idProduct": 7,
                      "quantity": 5
                    }
                  ]
                }
                ```
                
                **Ejemplo de una COMPRA:**
                ```json
                {
                  "idUser": 2,
                  "type": "PURCHASE",
                  "items": [
                    {
                      "idProduct": 4,
                      "quantity": 50
                    }
                  ]
                }
                ```
                
                **⚙¿Qué hace el sistema automáticamente?**
                - Si es SALE: resta el stock de los productos
                - Si es PURCHASE: suma el stock de los productos
                - Registra la fecha y hora actual
                - Calcula el total de la transacción
                """,
            responses = {
                    @ApiResponse(responseCode = "201", description = "Transacción creada exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos (verifica IDs de usuario/productos o cantidades)", content = @Content)
            }
    )
    @PostMapping("/save")
    public ResponseEntity<Transaction> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = """
                        **Campos requeridos:**
                        - `idUser`: ID del usuario que realiza la transacción
                        - `type`: Tipo de transacción (SALE o PURCHASE)
                        - `items`: Lista de productos con:
                          - `idProduct`: ID del producto
                          - `quantity`: Cantidad a vender/comprar
                        
                        **No incluyas:** objetos completos de User o Product, solo los IDs.
                        """,
                    required = true,
                    content = @Content(mediaType = "application/json")
            )
            @RequestBody Transaction transaction) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.save(transaction));
    }


    @Operation(
            summary = "Buscar una transacción por ID",
            description = """
                Obtiene los detalles completos de una transacción específica usando su identificador único.
                
                **¿Qué obtienes?**
                - Información del usuario que realizó la transacción
                - Lista de productos con cantidades y precios
                - Fecha y hora de la transacción
                - Tipo de transacción (venta o compra)
                - Total de la transacción
                
                **Útil para:** Ver detalles de una venta/compra específica o generar facturas.
                """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Transacción encontrada exitosamente"),
                    @ApiResponse(responseCode = "404", description = "No existe una transacción con ese ID", content = @Content)
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById(
            @Parameter(description = "ID único de la transacción que deseas consultar", example = "25")
            @PathVariable Integer id) {
        return transactionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Eliminar una transacción",
            description = """
                Elimina permanentemente una transacción del sistema por su ID.
                
                **Advertencia:**
                - Esta acción NO es reversible
                - El stock de productos NO se revierte automáticamente
                - Úsalo con precaución
                
                **Uso recomendado:** Solo para eliminar transacciones creadas por error.
                """,
            responses = {
                    @ApiResponse(responseCode = "204", description = "Transacción eliminada exitosamente"),
                    @ApiResponse(responseCode = "404", description = "No existe una transacción con ese ID", content = @Content)
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ID de la transacción que deseas eliminar", example = "25")
            @PathVariable Integer id) {
        transactionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Buscar transacciones por rango de fechas",
            description = """
                Filtra y obtiene todas las transacciones realizadas entre dos fechas específicas.
                
                **Formato de fechas:**
                - Usa el formato: `yyyy-MM-ddTHH:mm:ss`
                - Ejemplo: `2025-10-15T09:30:00`
                
                **Ejemplos de uso:**
                - Transacciones de hoy
                - Ventas del último mes
                - Compras de un trimestre específico
                - Movimientos de una semana
                
                **Tip:** Para obtener un día completo:
                - Start: `2025-10-15T00:00:00`
                - End: `2025-10-15T23:59:59`
                """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Transacciones filtradas exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Formato de fecha inválido (usa yyyy-MM-ddTHH:mm:ss)", content = @Content)
            }
    )
    @GetMapping("/search/by-date")
    public ResponseEntity<List<Transaction>> getByDateBetween(
            @Parameter(description = "Fecha y hora de inicio del rango de búsqueda", example = "2025-10-01T00:00:00")
            @RequestParam("start") LocalDateTime start,
            @Parameter(description = "Fecha y hora final del rango de búsqueda", example = "2025-10-31T23:59:59")
            @RequestParam("end") LocalDateTime end) {
        return ResponseEntity.ok(transactionService.findByDateBetween(start, end));
    }

    @Operation(
            summary = "Buscar transacciones por tipo",
            description = """
                Filtra las transacciones según su tipo: ventas o compras.
                
                **Tipos disponibles:**
                - `SALE` - Ventas realizadas a clientes (disminuye stock)
                - `PURCHASE` - Compras a proveedores (aumenta stock)
                
                **Casos de uso:**
                - Ver solo las ventas del día/mes
                - Consultar todas las compras realizadas
                - Generar reportes separados por tipo
                - Calcular totales de ventas o compras
                """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Transacciones filtradas exitosamente")
            }
    )
    @GetMapping("/search/by-type")
    public ResponseEntity<List<Transaction>> getByType(
            @Parameter(description = "Tipo de transacción: SALE (venta) o PURCHASE (compra)", example = "SALE")
            @RequestParam("type") TransactionType type) {
        return ResponseEntity.ok(transactionService.findByType(type));
    }
}