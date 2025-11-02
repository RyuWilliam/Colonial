package com.colonial.web.controller;

import com.colonial.domain.enums.ProductCategory;
import com.colonial.domain.model.Product;
import com.colonial.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Productos", description = "Gestión completa del inventario de productos")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(
            summary = "Obtener todos los productos",
            description = """
                Devuelve el listado completo de productos registrados en el inventario.
                
                **Incluye:**
                - Productos con stock disponible
                - Productos agotados
                - Toda la información: nombre, precio, categoría, stock, etc.
                
                **Útil para:** Ver el inventario completo o generar catálogos.
                """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente")
            }
    )
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @Operation(
            summary = "Crear un nuevo producto",
            description = """
                Registra un nuevo producto en el inventario del sistema.
                
                **📝 Ejemplo de producto:**
                ```json
                {
                  "name": "Aspirina 500mg",
                  "description": "Analgésico y antipirético",
                  "acquisitionPrice": 150.0,
                  "stock": 100,
                  "category": "MEDICINE"
                }
                ```
                
                **Campos requeridos:**
                - `name` - Nombre del producto (debe ser único)
                - `description` - Descripción o detalles del producto
                - `acquisitionPrice` - Precio de adquisición/compra
                - `stock` - Cantidad inicial en inventario
                - `category` - Categoría del producto (ver categorías disponibles abajo)
                
                **Categorías disponibles:**
                - `BEVERAGE` - Bebidas en general (jugos, gaseosas, agua, etc.)
                - `BEER` - Cervezas
                - `CIGARETTE` - Cigarrillos
                - `DAIRY` - Lácteos (leche, queso, yogurt, etc.)
                - `GROCERY` - Abarrotes (arroz, aceite, enlatados, etc.)
                - `SNACKS` - Mecato (papas, dulces, chocolates, etc.)
                - `CLEANING` - Productos de aseo y limpieza
                - `OTHER` - Otros productos
                
                **💡 Nota:** No necesitas enviar campos como `id` o fechas, el sistema los genera automáticamente.
                """,
            responses = {
                    @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos (verifica campos requeridos o nombre duplicado)", content = @Content)
            }
    )
    @PostMapping("/save")
    public ResponseEntity<Product> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = """
                        **Información del producto a registrar.**
                        
                        Solo incluye los campos básicos mencionados arriba.
                        El sistema automáticamente asigna el ID y las fechas.
                        """,
                    required = true,
                    content = @Content(mediaType = "application/json")
            )
            @RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @Operation(
            summary = "Buscar un producto por ID",
            description = """
                Obtiene la información completa de un producto específico usando su identificador único.
                
                **¿Qué obtienes?**
                - Nombre y descripción
                - Precio de adquisición
                - Stock disponible
                - Categoría
                - Fechas de creación y actualización
                
                **Útil para:** Consultar detalles antes de realizar una venta o actualización.
                """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Producto encontrado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "No existe un producto con ese ID", content = @Content)
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(
            @Parameter(description = "ID único del producto que deseas consultar", example = "101")
            @PathVariable Integer id) {

        Product product = productService.findById(id).orElse(null);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @Operation(
            summary = "Eliminar un producto",
            description = """
                Elimina permanentemente un producto del inventario por su ID.
                
                **⚠️ Advertencias:**
                - Esta acción NO es reversible
                - No podrás crear transacciones con este producto después de eliminarlo
                - Si tiene transacciones previas, esas se mantienen en el historial
                
                **Uso recomendado:** Solo para productos creados por error o descontinuados definitivamente.
                """,
            responses = {
                    @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "No existe un producto con ese ID", content = @Content)
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ID del producto que deseas eliminar", example = "101")
            @PathVariable Integer id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Buscar productos por categoría",
            description = """
                Filtra y obtiene todos los productos que pertenecen a una categoría específica.
                
                **Categorías disponibles:**
                - `BEVERAGE` - Bebidas (jugos, gaseosas, agua, etc.)
                - `BEER` - Cervezas y licores de cerveza
                - `CIGARETTE` - Cigarrillos y productos de tabaco
                - `DAIRY` - Lácteos (leche, queso, yogurt, mantequilla, etc.)
                - `GROCERY` - Abarrotes y mercado general (arroz, aceite, granos, enlatados, etc.)
                - `SNACKS` - Mecato, papas, dulces, chocolates y golosinas
                - `CLEANING` - Productos de aseo, limpieza e higiene del hogar
                - `OTHER` - Otros productos no clasificados
                
                **Casos de uso:**
                - Ver todas las cervezas disponibles
                - Consultar el inventario de lácteos
                - Generar reportes por categoría
                - Filtrar productos de mecato para promociones
                """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Productos filtrados exitosamente")
            }
    )
    @GetMapping("/search/by-category")
    public ResponseEntity<List<Product>> getByCategory(
            @Parameter(description = "Categoría de productos a filtrar (BEVERAGE, BEER, CIGARETTE, DAIRY, GROCERY, SNACKS, CLEANING, OTHER)", example = "BEER")
            @RequestParam("category") ProductCategory category) {
        return ResponseEntity.ok(productService.findAllByCategory(category));
    }

    @Operation(
            summary = "Buscar productos por nombre (búsqueda parcial)",
            description = """
                Busca productos cuyo nombre contenga el texto especificado. No necesita ser el nombre exacto.
                
                **Características:**
                - Búsqueda flexible (no necesita ser exacto)
                - No distingue entre mayúsculas y minúsculas
                - Encuentra coincidencias parciales
                
                **Ejemplos:**
                - Si buscas "cocacola" encontrará "Cocacola", "Cocacola Light", "Cocacola 2L", etc.
                - Si buscas "corona" encontrará "Corona", "Coronita", "Corona Extra", etc.
                - Si buscas "arroz" encontrará "Arroz Diana", "Arroz Roa", etc.
                
                **Útil para:** Buscar productos cuando no recuerdas el nombre exacto.
                """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Productos encontrados exitosamente (puede ser una lista vacía)")
            }
    )
    @GetMapping("/search/by-name")
    public ResponseEntity<List<Product>> getAllByName(
            @Parameter(description = "Parte del nombre o nombre completo del producto", example = "Aspirin")
            @RequestParam("name") String name) {
        return ResponseEntity.ok(productService.findAllByName(name));
    }

    @Operation(
            summary = "Buscar producto por nombre exacto",
            description = """
                Busca UN SOLO producto que coincida exactamente con el nombre especificado.
                
                **⚠️ Importante:**
                - El nombre debe ser EXACTO (incluyendo mayúsculas/minúsculas)
                - Solo devuelve UN producto
                - Si no existe, devuelve error 404
                
                **Diferencia con `/search/by-name`:**
                - Este endpoint: nombre EXACTO → 1 producto
                - `/search/by-name`: nombre PARCIAL → lista de productos
                
                **Útil para:** Verificar si un producto específico ya existe en el inventario.
                """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Producto encontrado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "No existe un producto con ese nombre exacto", content = @Content)
            }
    )
    @GetMapping("/search/name")
    public ResponseEntity<Product> getByName(
            @Parameter(description = "Nombre EXACTO del producto a buscar", example = "Paracetamol")
            @RequestParam("name") String name) {
        return productService.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Obtener productos con stock bajo",
            description = """
                Lista todos los productos cuyo stock está por debajo de un límite especificado.
                
                **📊 ¿Para qué sirve?**
                - Identificar productos que necesitan reabastecimiento
                - Prevenir quedarse sin stock
                - Planificar compras a proveedores
                
                **Ejemplo:**
                - Si pones threshold = 10
                - Te mostrará todos los productos con menos de 10 unidades
                
                **💡 Tip:** Usa un threshold adecuado según tu tipo de producto:
                - Medicamentos de alta rotación: threshold = 50
                - Productos de baja rotación: threshold = 10
                """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de productos con stock bajo obtenida exitosamente")
            }
    )
    @GetMapping("/search/low-stock")
    public ResponseEntity<List<Product>> getLowStock(
            @Parameter(description = "Cantidad mínima de stock. Mostrará productos por debajo de este número", example = "10")
            @RequestParam("threshold") Integer threshold) {
        return ResponseEntity.ok(productService.findProductsWithLowStock(threshold));
    }

    @Operation(
            summary = "Obtener productos disponibles",
            description = """
                Devuelve todos los productos que tienen stock disponible (mayor a 0).
                
                **¿Qué incluye?**
                - Productos con al menos 1 unidad en stock
                - Todos listos para ser vendidos
                
                **¿Qué NO incluye?**
                - Productos con stock = 0 (agotados)
                
                **Útil para:**
                - Mostrar catálogo de productos disponibles
                - Realizar ventas
                - Generar reportes de inventario activo
                """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de productos disponibles obtenida exitosamente")
            }
    )
    @GetMapping("/search/available")
    public ResponseEntity<List<Product>> getAvailable() {
        return ResponseEntity.ok(productService.findAvailableProducts());
    }

    @Operation(
            summary = "Obtener productos agotados",
            description = """
                Devuelve todos los productos sin stock disponible (stock = 0).
                
                **¿Para qué sirve?**
                - Identificar productos que necesitan reabastecimiento urgente
                - Ver qué productos no se pueden vender actualmente
                - Generar órdenes de compra prioritarias
                
                **💡 Acción recomendada:**
                Si hay productos agotados, considera crear transacciones de tipo PURCHASE para reabastecer.
                """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de productos agotados obtenida exitosamente")
            }
    )
    @GetMapping("/search/out-of-stock")
    public ResponseEntity<List<Product>> getOutOfStock() {
        return ResponseEntity.ok(productService.findOutOfStockProducts());
    }

    @Operation(
            summary = "Verificar si existe un producto",
            description = """
                Verifica si un producto existe en el inventario usando su ID.
                
                **Respuestas:**
                - `true` - El producto existe
                - `false` - El producto NO existe
                
                **Útil para:**
                - Validar IDs antes de crear transacciones
                - Verificar productos antes de actualizaciones
                - Comprobar existencia sin obtener todos los datos del producto
                
                **💡 Ventaja:** Es más rápido que buscar por ID completo cuando solo necesitas saber si existe.
                """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "Verificación completada (true o false)")
            }
    )
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsById(
            @Parameter(description = "ID del producto que deseas verificar", example = "101")
            @PathVariable Integer id) {
        return ResponseEntity.ok(productService.existsById(id));
    }
}