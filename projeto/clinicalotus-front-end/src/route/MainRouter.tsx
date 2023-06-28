import React, {useContext} from 'react';
import {Route, Routes} from "react-router-dom";
import {Login, PublicPage} from "../pages";
import {AuthContext} from "../store/store";

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
                        <Route path={'/'} element={<div>Dashboard</div>}>
                        </Route>
                    )
            }

        </Routes>
    )
}

export default MainRouter;