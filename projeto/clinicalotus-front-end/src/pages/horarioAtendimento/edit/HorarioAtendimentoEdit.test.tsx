import React from 'react'
import { render, screen } from '@testing-library/react'
import HorarioAtendimento from "./HorarioAtendimentoEdit";

test('renders learn react link', () => {
  render(<HorarioAtendimento />)
  const linkElement = screen.getByText(/learn react/i)
  expect(linkElement).toBeInTheDocument()
})
