package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.ir.symbols.IrClassSymbol
import org.jetbrains.kotlin.ir.symbols.IrEnumEntrySymbol
import org.jetbrains.kotlin.ir.symbols.IrExternalPackageFragmentSymbol
import org.jetbrains.kotlin.ir.symbols.IrFieldSymbol
import org.jetbrains.kotlin.ir.symbols.IrFileSymbol
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol
import org.jetbrains.kotlin.ir.symbols.IrTypeParameterSymbol
import org.jetbrains.kotlin.ir.symbols.IrValueParameterSymbol
import org.jetbrains.kotlin.ir.symbols.IrVariableSymbol
import org.jetbrains.kotlin.ir.util.SymbolRenamer
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

class MetaSymbolRenamer(val delegate: SymbolRenamer) : SymbolRenamer by delegate {
  override fun getClassName(symbol: IrClassSymbol): Name {
    println("MetaSymbolRenamer.getClassName: $symbol")
    return delegate.getClassName(symbol)
  }

  override fun getEnumEntryName(symbol: IrEnumEntrySymbol): Name {
    println("MetaSymbolRenamer.getEnumEntryName: $symbol")
    return delegate.getEnumEntryName(symbol)
  }

  override fun getExternalPackageFragmentName(symbol: IrExternalPackageFragmentSymbol): FqName {
    println("MetaSymbolRenamer.getExternalPackageFragmentName: $symbol")
    return delegate.getExternalPackageFragmentName(symbol)
  }

  override fun getFieldName(symbol: IrFieldSymbol): Name {
    println("MetaSymbolRenamer.getFieldName: $symbol")
    return delegate.getFieldName(symbol)
  }

  override fun getFileName(symbol: IrFileSymbol): FqName {
    println("MetaSymbolRenamer.getFileName: $symbol")
    return delegate.getFileName(symbol)
  }

  override fun getFunctionName(symbol: IrSimpleFunctionSymbol): Name {
    println("MetaSymbolRenamer.getFunctionName: $symbol")
    return delegate.getFunctionName(symbol)
  }

  override fun getTypeParameterName(symbol: IrTypeParameterSymbol): Name {
    println("MetaSymbolRenamer.getTypeParameterName: $symbol")
    return delegate.getTypeParameterName(symbol)
  }

  override fun getValueParameterName(symbol: IrValueParameterSymbol): Name {
    println("MetaSymbolRenamer.getValueParameterName: $symbol")
    return delegate.getValueParameterName(symbol)
  }

  override fun getVariableName(symbol: IrVariableSymbol): Name {
    println("MetaSymbolRenamer.getVariableName: $symbol")
    return delegate.getVariableName(symbol)
  }
}