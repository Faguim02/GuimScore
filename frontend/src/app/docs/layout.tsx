import React from 'react';
import Sidebar from '@/components/Sidebar';

export default function DocsLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <div className="flex">
      <Sidebar />
      <main className="flex-1 ml-64 p-4"> {/* Added ml-64 to push content */}
        {children}
      </main>
    </div>
  );
}
