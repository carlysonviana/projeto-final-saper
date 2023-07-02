import {useContext, useMemo} from "react";
import Axios from "axios";
import {AuthContext} from "../store/store";
const useAPI = () => {

    const URL_BASE = 'http://localhost:8080/';
    const auth = useContext(AuthContext);

    const configDefault = useMemo(() => {
        const credentials = btoa(auth.user?.login + ':' + auth.user?.senha)
        return {
            headers: {
                Authorization: 'Basic ' + credentials
            },
        }
    }, [auth.user])
    const get = async (url: string, params?: any, config?: any) =>{
        console.log(URL_BASE + url);
        try {
            const response = await Axios.get(URL_BASE + url, config? config: {...configDefault, params});
            return response.data;
        }
        catch (e) {
            console.log(e);
        }
    }

    const post = async (url: string, body: any, config?: any) =>{
        try {
            const response = await Axios.post(URL_BASE + url, body,  config? config: {...configDefault});
            return response.data;
        }
        catch (e) {
            console.log(e);
        }
    }

    const put = async (url: string, body: any, config?: any) =>{
        try {
            const response = await Axios.put(URL_BASE + url, body,  config? config: {...configDefault});
            return response.data;
        }
        catch (e) {
            console.log(e);
        }
    }

    const _delete = async (url: string, params?: any, config?: any) =>{
        try {
            const response = await Axios.delete(URL_BASE + url, config? config: {...configDefault, params});
            return response.data;
        }
        catch (e) {
            console.log(e);
        }
    }

    return {get, post, put, delete: _delete}
}

export default useAPI;