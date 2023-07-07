import React from 'react'
import { render, screen } from '@testing-library/react'
import Cidade from "./Cidade";

test('renders learn react link', () => {
  render(<Cidade />)
  const linkElement = screen.getByText(/learn react/i)
  expect(linkElement).toBeInTheDocument()
})
