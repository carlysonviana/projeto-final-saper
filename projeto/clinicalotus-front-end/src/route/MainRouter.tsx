import React, {useContext} from 'react';
import {Outlet, Route, Routes} from "react-router-dom";
import {Login,
        PublicPage,
        Paciente,
        Consulta,
        HorarioAtendimento,
        } from "../pages";
import {AuthContext} from "../store/store";
import {BaseLayout} from "../components/layout";


function MainRouter(){
    const auth = useContext(AuthContext);
    return (
        <Routes>
            {
                !auth.user?
                    (
                        <>
                            <Route path={'/'} element={<PublicPage/>}>
                            </Route>
                            <Route path={'/login'} element={<Login/>}>
                            </Route>
                        </>
                    ) :
                    (
                        <Route path={'/'} element={<BaseLayout/>}>
                            <Route path={'/pacientes'} element={<Paciente/>}>
                            </Route>
                            <Route path={'/consultas'} element={<Consulta/>}>
                            </Route>
                            <Route path={'/horarios'} element={<HorarioAtendimento/>}>
                            </Route>
                        </Route>
                    )
            }

        </Routes>
    )
}

export default MainRouter;