import React from 'react'
import { render, screen } from '@testing-library/react'
import ConsultaEdit from "./ConsultaEdit";

test('renders learn react link', () => {
  render(<ConsultaEdit />)
  const linkElement = screen.getByText(/learn react/i)
  expect(linkElement).toBeInTheDocument()
})
