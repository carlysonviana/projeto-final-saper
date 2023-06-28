import React from 'react';

export type User = {
    "id": number,
    "nome": string,
    "cpf": string,
    "email": string,
    "telefone": string,
    "celular": string,
    "dataNascimento": string,
    "dataAdmissao": string,
    "endereco_id": number,
    "categoriaFuncionario_id": number
}


type AuthProps = {
    user?: User,
    setUser?: (user: User | undefined) => void
}

const defaultValue: AuthProps = {

}

export const AuthContext = React.createContext(defaultValue);