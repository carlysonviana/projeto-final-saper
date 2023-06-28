import React from 'react'
import { render, screen } from '@testing-library/react'
import Endereco from "./Endereco";

test('renders learn react link', () => {
  render(<Endereco />)
  const linkElement = screen.getByText(/learn react/i)
  expect(linkElement).toBeInTheDocument()
})
