export type Consulta = {
    "consulta_id": number,
    "dataHora": string,
    "autorizacaoPlano": boolean,
    "paciente_id": number,
    "medico_id": number,
    "confirmada": boolean
}

export type ConsultaAddForm = {
    "dataHora": string,
    "autorizacaoPlano": boolean,
    "paciente_id": number,
    "medico_id": number
}

export type ConsultaEditForm = {
    "dataHora": string,
    "autorizacaoPlano": boolean
}