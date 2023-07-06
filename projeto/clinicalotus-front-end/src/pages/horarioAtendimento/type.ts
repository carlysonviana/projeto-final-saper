export type HorarioAtendimento = {
    "horario_id": number,
    "diaDaSemana": string,
    "horarioInicio": string,
    "horarioFim": string,
    "medico_id"?: number
}

export type HorarioAtendimentoAddForm = {
    "diaDaSemana": string,
    "horarioInicio": string,
    "horarioFim": string,
    "medico_id"?: number
}

export type HorarioAtendimentoEditForm = {
    "diaDaSemana": string,
    "horarioInicio": string,
    "horarioFim": string
}