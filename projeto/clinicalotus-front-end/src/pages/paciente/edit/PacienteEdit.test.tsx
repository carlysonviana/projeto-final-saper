import React from 'react'
import { render, screen } from '@testing-library/react'
import PacienteEdit from "./PacienteEdit";

test('renders learn react link', () => {
  render(<PacienteEdit />)
  const linkElement = screen.getByText(/learn react/i)
  expect(linkElement).toBeInTheDocument()
})
