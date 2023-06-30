import React from 'react';
import styles from './BaseLayout.module.scss';
import {Link, Outlet} from "react-router-dom";
import SideBar from "../sideBar/SideBar";
function BaseLayout(){
    return(
        <div className={styles.container}>
            <div className={"d-flex flex-column flex-shrink-0 p-3 text-bg-dark " + styles.sidebar}>
                <SideBar/>
            </div>
            <Outlet/>
        </div>

    )
}

export default BaseLayout;