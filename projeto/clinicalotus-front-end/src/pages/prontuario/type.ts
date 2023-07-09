export type Prontuario = {
    "id": number,
    "receituario": string,
    "diagnostico": string,
    "paciente_id": number
}

export type ProntuarioAddForm = {
    "receituario": string,
    "diagnostico": string,
    "paciente_id": number
}

export type ProntuarioEditForm = {
    "receituario": string,
    "diagnostico": string
}

export type ProntuarioViewForm = {
    "receituario": string,
    "diagnostico": string
}