import React from 'react';
import styles from './BaseLayout.module.scss';
import {Link, Outlet} from "react-router-dom";
import SideBar from "../sideBar/SideBar";
import {Navbar} from "react-bootstrap";
import logo from '../../../assets/imgs/logo-clinica-lotus.png';
function BaseLayout(){
    return(
        <>
            <Navbar className={styles.navbar}>
                <img className={styles.centeredImage} src={logo} alt={'Logo'}/>
            </Navbar>
            <div className={styles.container +' '+ styles.backgroundImage}>
                <div className={"d-flex flex-column flex-shrink-0 p-3 " + styles.sidebar}>
                    <SideBar/>
                </div>
                <Outlet/>
            </div>

        </>
    )
}

export default BaseLayout;