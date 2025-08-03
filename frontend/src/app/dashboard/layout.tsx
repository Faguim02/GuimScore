"use client"

import Link from "next/link"
import { useState } from "react"
import {
  CircleUser,
  Menu,
  Package2,
  Server,
  KeyRound,
  Book,
} from "lucide-react"

import { Button } from "@/components/ui/button"

export default function DashboardLayout({
  children,
}: {
  children: React.ReactNode
}) {
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false)
  const [activeLink, setActiveLink] = useState("game-servers")

  const navLinks = [
    { href: "/dashboard", label: "Game Servers", icon: Server, id: "game-servers" },
    { href: "/dashboard/api-keys", label: "API Keys", icon: KeyRound, id: "api-keys" },
    { href: "/docs", label: "Docs", icon: Book, id: "docs" },
  ]

  const SidebarNav = () => (
    <nav className="grid items-start px-2 text-sm font-medium lg:px-4">
      {navLinks.map((link) => (
        <Link
          key={link.id}
          href={link.href}
          onClick={() => setActiveLink(link.id)}
          className={`flex items-center gap-3 rounded-lg px-3 py-2 transition-all hover:text-primary ${
            activeLink === link.id
              ? "bg-muted text-primary dark:bg-gray-800 dark:text-primary-foreground"
              : "text-muted-foreground"
          }`}
        >
          <link.icon className="h-4 w-4" />
          {link.label}
        </Link>
      ))}
    </nav>
  )

  return (
    <div className="grid min-h-screen w-full md:grid-cols-[220px_1fr] lg:grid-cols-[280px_1fr]">
      {/* Desktop Sidebar */}
      <aside className="hidden border-r bg-muted/40 md:block dark:bg-gray-900">
        <div className="flex h-full max-h-screen flex-col gap-2">
          <div className="flex h-14 items-center border-b px-4 lg:h-[60px] lg:px-6 dark:border-gray-700">
            <Link href="/" className="flex items-center gap-2 font-semibold dark:text-white">
              <Package2 className="h-6 w-6" />
              <span>GuimScore</span>
            </Link>
          </div>
          <div className="flex-1">
            <SidebarNav />
          </div>
        </div>
      </aside>

      <div className="flex flex-col">
        {/* Mobile Header */}
        <header className="flex h-14 items-center gap-4 border-b bg-muted/40 px-4 lg:h-[60px] lg:px-6">
          <button
            className="shrink-0 md:hidden"
            onClick={() => setIsMobileMenuOpen(!isMobileMenuOpen)}
          >
            <Menu className="h-5 w-5" />
            <span className="sr-only">Toggle navigation menu</span>
          </button>
          <div className="w-full flex-1">
            {/* Can add search or other elements here */}
          </div>
          <Button variant="secondary" size="icon" className="rounded-full">
            <CircleUser className="h-5 w-5" />
            <span className="sr-only">Toggle user menu</span>
          </Button>
        </header>

        {/* Mobile Sidebar (Sheet) */}
        {isMobileMenuOpen && (
          <div className="fixed inset-0 z-50 bg-black/60 md:hidden" onClick={() => setIsMobileMenuOpen(false)}>
            <div className="fixed left-0 top-0 h-full w-3/4 max-w-sm bg-white dark:bg-gray-950 p-4 transition-transform duration-300 ease-in-out">
              <Link
                href="#"
                className="flex items-center gap-2 text-lg font-semibold mb-4 dark:text-white"
              >
                <Package2 className="h-6 w-6" />
                <span>GuimScore</span>
              </Link>
              <SidebarNav />
            </div>
          </div>
        )}

        <main className="flex flex-1 flex-col gap-4 p-4 lg:gap-6 lg:p-6">
          {children}
        </main>
      </div>
    </div>
  )
}
