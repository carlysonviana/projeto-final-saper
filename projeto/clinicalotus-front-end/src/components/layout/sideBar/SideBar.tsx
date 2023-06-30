import React, {useContext} from 'react';
import styles from './SideBar.module.scss';
import {Link, useNavigate} from "react-router-dom";
import {AuthContext} from "store/store";
function SideBar(){
    const auth = useContext(AuthContext);
    const navigate = useNavigate();

    function handleSingOut() {
        if(auth.setUser) auth.setUser(undefined);
        navigate('/')
    }

    return(
        <>
            <Link to="/" className="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                <svg className="bi pe-none me-2" width="40" height="32">
                </svg>
                <span className="fs-4">Sidebar</span>
            </Link>
            <hr/>
            <ul className="nav nav-pills flex-column mb-auto">
                <li className="nav-item">
                    <Link to="/" className="nav-link active" aria-current="page">
                        Home
                    </Link>
                </li>
                <li>
                    <Link to="/pacientes" className="nav-link text-white">
                        Paciente
                    </Link>
                </li>
                <li>
                    <Link to="/consultas" className="nav-link text-white">
                        Consulta
                    </Link>
                </li>
                <li>
                    <Link to="/horarios" className="nav-link text-white">
                        Horarios Atendimento
                    </Link>
                </li>
            </ul>
            <hr/>
            <div className="dropdown">
                <a href="#"
                   className="d-flex align-items-center text-white text-decoration-none dropdown-toggle"
                   data-bs-toggle="dropdown" aria-expanded="false">
                    <img src="https://github.com/mdo.png" alt="" width="32" height="32"
                         className="rounded-circle me-2"/>
                    <strong>{auth.user?.nome}</strong>
                </a>
                <ul className="dropdown-menu dropdown-menu-dark text-small shadow">
                    <li><a className="dropdown-item" href="#">Perfil</a></li>
                    <li>
                        <hr className="dropdown-divider"/>
                    </li>
                    <li><div onClick={handleSingOut} className={"dropdown-item " + styles.signOut}>Sair</div></li>
                </ul>
            </div>
        </>
    )
}

export default SideBar;