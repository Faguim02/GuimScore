import * as React from "react"
import { cn } from "@/lib/utils"

function CardCode({ children }: { children: React.ReactNode }) {
    return (
        <pre className="bg-gray-800 text-white p-4 rounded-md mb-6 overflow-x-auto">
            <code className="language-json">
                {children}
            </code>
        </pre>
    );
}

export { CardCode }