<nav id="barra-lateral" class="shadow-sm overflow-auto">
    <style>
        #barra-lateral {
            height: 100%;
            background-color: #f1f1f1;
        }

        #barra-lateral >ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
        }

        #barra-lateral >ul>li a {
            display: block;
            color: #000;
            padding: 8px 16px;
            text-decoration: none;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        /* Change the link color on hover */
        #barra-lateral ul li a:hover {
            background-color: #555;
            color: white;
        }

    </style>
    <ul>
        <li>
            <a href="emision-proforma">
                <span>Emitir Proforma</span>
                <i class="fas fa-file-alt"></i>
            </a>

        </li>
        <li>
            <a href="emision-pedido">
                <span>Emitir Pedido</span>
                <i class="fas fa-file-powerpoint"></i>
            </a>
        </li>
        <li>
            <a href="pedidos">
                <span>Listado Pedidos</span>
                <i class="fas fa-copy"></i>
            </a>
        </li>
        <li>
            <a href="buscar-proforma">
                <span>Generar Venta</span>
                <i class="fas fa-file-export"></i>
            </a>

        </li>
        <li>
            <a href="buscar-venta-reporte">
                <span>Generar Reporte</span>
                <i class="fas fa-copy"></i>
            </a>
        </li>
        <hr />
        <li>
            <a href="proveedores">
                <span>Gestionar Proveedores</span>
                <i class="fas fa-people-carry"></i>
            </a>
        </li>
        <li>
            <a href="productos">
                <span>Gestionar Productos</span>
                <i class="fas fa-boxes"></i>
            </a>
        </li>
        <hr />
        <li>
            <a href="roles">
                <span>Gestionar Roles</span>
                <i class="fas fa-key"></i>
            </a>
        </li>
        <li>
            <a href="usuarios">
                <span>Gestionar Usuarios</span>
                <i class="fas fa-users"></i>
            </a>
        </li>
    </ul>
</nav>
