import React, {useContext} from 'react';
import styles from './SideBar.module.scss';
import {Link, useNavigate} from "react-router-dom";
import {AuthContext} from "store/store";
import {HiUserGroup} from "react-icons/hi";
import {LiaStethoscopeSolid} from "react-icons/lia";
import {LuCalendarClock} from "react-icons/lu";
function SideBar(){
    const auth = useContext(AuthContext);
    const navigate = useNavigate();

    function handleSingOut() {
        if(auth.setUser) auth.setUser(undefined);
        navigate('/')
    }

    //Roles 1- Admin, 2- Médico e 3- Recepcionista
    const routes: any = {
         1: [
             {
                 path: 'pacientes',
                 icon: <HiUserGroup/>,
                 text: 'Pacientes'
             },
             {
                 path: 'consultas',
                 icon: <LiaStethoscopeSolid/>,
                 text: 'Consultas'
             },
             {
                 path: 'horarios',
                 icon: <LuCalendarClock/>,
                 text: 'Horarios'
             }
         ],
         2: [
             {
                 path: 'pacientes',
                 icon: <HiUserGroup/>,
                 text: 'Pacientes'
             },
             {
                 path: 'horarios',
                 icon: <LuCalendarClock/>,
                 text: 'Horarios'
             }
         ],
         3: [
             {
                 path: 'pacientes',
                 icon: <HiUserGroup/>,
                 text: 'Pacientes'
             }
         ],
         4: []
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
                    <Link to="/" className="nav-link text-white" aria-current="page">
                        Home
                    </Link>
                </li>
                {
                    routes[auth.user?.categoriaFuncionario_id || 4].map((route: any) => {
                        return (
                            <li className="nav-item" key={route.path}>
                                <Link to={route.path} className="nav-link text-white" aria-current="page">
                                    {route.icon}{' '}
                                    {route.text}
                                </Link>
                            </li>
                        )
                    })
                }
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