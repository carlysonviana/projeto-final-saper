// import React, { useContext } from 'react';
// import { Outlet, Route, Routes } from "react-router-dom";
// import {
//     Login,
//     ProfileEdit,
//     PublicPage,
//     PacienteList,
//     PacienteEdit,
//     PacienteAdd,
//     ConsultaAdd,
//     ConsultaEdit,
//     ConsultaList,
//     HorarioAtendimentoAdd,
//     HorarioAtendimentoList,
//     HorarioAtendimentoEdit,
//     FuncionarioList,
//     FuncionarioEdit,
//     FuncionarioAdd
// } from "../pages";
// import { AuthContext } from "../store/store";
// import { BaseLayout } from "../components/layout";
//
//
// function MainRouter() {
//     const auth = useContext(AuthContext);
//     return (
//         <Routes>
//             {
//                 !auth.user ?
//                     (
//                         <>
//                             <Route path={'/'} element={<PublicPage />}>
//                             </Route>
//                             <Route path={'login'} element={<Login />}>
//                             </Route>
//                         </>
//                     ) :
//                     (
//                         <Route path={'/'} element={<BaseLayout />}>
//                             <Route path={'pacientes'} element={<Outlet />}>
//                                 <Route path={''} element={<PacienteList />}>
//                                 </Route>
//                                 <Route path={'add'} element={<PacienteAdd />}>
//                                 </Route>
//                                 <Route path={'edit/:id'} element={<PacienteEdit />}>
//                                 </Route>
//                             </Route>
//
//                             <Route path={'funcionarios'} element={<Outlet/>}>
//                                 <Route path={''} element={<FuncionarioList/>}>
//                                 </Route>
//                                 <Route path={'add'} element={<FuncionarioAdd/>}>
//                                 </Route>
//                                 <Route path={'edit/:id'} element={<FuncionarioEdit/>}>
//                                 </Route>
//                             </Route>
//                             <Route path={'consultas'} element={<Outlet/>}>
//                                 <Route path={''} element={<ConsultaList/>}>
//                                 </Route>
//                                 <Route path={'add'} element={<ConsultaAdd />}>
//                                 </Route>
//                                 <Route path={'edit/:id'} element={<ConsultaEdit />}>
//                                 </Route>
//                             </Route>
//
//                             <Route path={'horarios'} element={<Outlet />}>
//                                 <Route path={''} element={<HorarioAtendimentoList />}>
//                                 </Route>
//                                 <Route path={'add'} element={<HorarioAtendimentoAdd />}>
//                                 </Route>
//                                 <Route path={'edit/:id'} element={<HorarioAtendimentoEdit />}>
//                                 </Route>
//                             <Route path={'profile'} element={<ProfileEdit/>}>
//                             </Route>
//                         </Route>
//                     )}
//         </Routes>
//         );
//
// }
//
// export default MainRouter;

import React, { useContext } from 'react';
import { Outlet, Route, Routes } from "react-router-dom";
import {
    Login,
    ProfileEdit,
    PublicPage,
    PacienteList,
    PacienteEdit,
    PacienteAdd,
    ConsultaAdd,
    ConsultaEdit,
    ConsultaList,
    HorarioAtendimentoAdd,
    HorarioAtendimentoList,
    HorarioAtendimentoEdit,
    FuncionarioList,
    FuncionarioEdit,
    FuncionarioAdd,
    ProntuarioAdd,
    ProntuarioEdit,
    ProntuarioList,
    ProntuarioView
} from "../pages";
import { AuthContext } from "../store/store";
import { BaseLayout } from "../components/layout";

function MainRouter() {
    const auth = useContext(AuthContext);

    return (
        <Routes>
            {
                !auth.user ?
                    (
                        <>
                            <Route path="/" element={<PublicPage />} />
                            <Route path="login" element={<Login />} />
                        </>
                    ) :
                    (
                        <Route path="/" element={<BaseLayout />}>
                            <Route path="pacientes" element={<Outlet />}>
                                <Route path="" element={<PacienteList />} />
                                <Route path="add" element={<PacienteAdd />} />
                                <Route path="edit/:id" element={<PacienteEdit />} />
                            </Route>

                            <Route path="funcionarios" element={<Outlet />}>
                                <Route path="" element={<FuncionarioList />} />
                                <Route path="add" element={<FuncionarioAdd />} />
                                <Route path="edit/:id" element={<FuncionarioEdit />} />
                            </Route>

                            <Route path="consultas" element={<Outlet />}>
                                <Route path="" element={<ConsultaList />} />
                                <Route path="add" element={<ConsultaAdd />} />
                                <Route path="edit/:id" element={<ConsultaEdit />} />
                            </Route>

                            <Route path="horarios" element={<Outlet />}>
                                <Route path="" element={<HorarioAtendimentoList />} />
                                <Route path="add" element={<HorarioAtendimentoAdd />} />
                                <Route path="edit/:id" element={<HorarioAtendimentoEdit />} />
                            </Route>

                            <Route path="prontuarios" element={<Outlet />}>
                                <Route path="" element={<ProntuarioList />} />
                                <Route path="add" element={<ProntuarioAdd />} />
                                <Route path="edit/:id" element={<ProntuarioEdit />} />
                                <Route path="view/:id" element={<ProntuarioView />} />
                            </Route>

                            <Route path="profile" element={<ProfileEdit />} />
                        </Route>
                    )}
        </Routes>
    );
}

export default MainRouter;
