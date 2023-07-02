import React, {useContext} from 'react';
import {Outlet, Route, Routes} from "react-router-dom";
import {
    Login,
    PublicPage,
    PacienteList,
    Consulta,
    HorarioAtendimento, PacienteEdit, PacienteAdd,
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
                            <Route path={'login'} element={<Login/>}>
                            </Route>
                        </>
                    ) :
                    (
                        <Route path={'/'} element={<BaseLayout/>}>
                            <Route path={'pacientes'} element={<Outlet/>}>
                                <Route path={''} element={<PacienteList/>}>
                                </Route>
                                <Route path={'add'} element={<PacienteAdd/>}>
                                </Route>
                                <Route path={'edit/:id'} element={<PacienteEdit/>}>
                                </Route>
                            </Route>
                            <Route path={'consultas'} element={<Consulta/>}>
                            </Route>
                            <Route path={'horarios'} element={<HorarioAtendimento/>}>
                            </Route>
                        </Route>
                    )
            }

        </Routes>
    )
}

export default MainRouter;