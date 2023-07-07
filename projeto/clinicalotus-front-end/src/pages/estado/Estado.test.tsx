import React from 'react'
import { render, screen } from '@testing-library/react'
import Estado from "./Estado";

test('renders learn react link', () => {
  render(<Estado />)
  const linkElement = screen.getByText(/learn react/i)
  expect(linkElement).toBeInTheDocument()
})
